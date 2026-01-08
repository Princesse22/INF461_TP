/**
 * Banque System - JavaScript principal
 * Fonctions utilitaires et gestion de l'interface
 */

// Configuration globale
const BanqueApp = {
    config: {
        apiBaseUrl: '/api',
        wsUrl: 'ws://localhost:8080/ws',
        appName: 'BanqueSystem',
        version: '1.0.0'
    },
    
    // État de l'application
    state: {
        isAuthenticated: false,
        user: null,
        notifications: [],
        unreadCount: 0
    },
    
    // Initialisation
    init: function() {
        console.log(`${this.config.appName} v${this.config.version} initialisé`);
        
        // Initialiser les composants
        this.initTooltips();
        this.initAlerts();
        this.initForms();
        this.initTables();
        
        // Vérifier l'authentification
        this.checkAuth();
        
        // Charger les notifications si connecté
        if (this.state.isAuthenticated) {
            this.loadNotifications();
        }
        
        // Écouter les événements
        this.setupEventListeners();
    },
    
    // Initialiser les tooltips Bootstrap
    initTooltips: function() {
        const tooltipTriggerList = [].slice.call(
            document.querySelectorAll('[data-bs-toggle="tooltip"]')
        );
        tooltipTriggerList.map(function(tooltipTriggerEl) {
            return new bootstrap.Tooltip(tooltipTriggerEl);
        });
    },
    
    // Initialiser les alertes auto-fermantes
    initAlerts: function() {
        const alerts = document.querySelectorAll('.alert-auto-dismiss');
        alerts.forEach(alert => {
            setTimeout(() => {
                if (alert && alert.parentNode) {
                    bootstrap.Alert.getInstance(alert)?.close();
                }
            }, 5000);
        });
    },
    
    // Initialiser les formulaires
    initForms: function() {
        // Validation des formulaires
        const forms = document.querySelectorAll('.needs-validation');
        forms.forEach(form => {
            form.addEventListener('submit', event => {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
        
        // Masques d'entrée
        this.initInputMasks();
    },
    
    // Initialiser les masques de saisie
    initInputMasks: function() {
        // Masque pour les montants
        const amountInputs = document.querySelectorAll('input[data-mask="amount"]');
        amountInputs.forEach(input => {
            input.addEventListener('input', function(e) {
                let value = e.target.value.replace(/[^\d.,]/g, '');
                value = value.replace(',', '.');
                e.target.value = value;
            });
        });
        
        // Masque pour les numéros de téléphone
        const phoneInputs = document.querySelectorAll('input[data-mask="phone"]');
        phoneInputs.forEach(input => {
            input.addEventListener('input', function(e) {
                let value = e.target.value.replace(/[^\d+]/g, '');
                if (!value.startsWith('+')) {
                    value = '+' + value;
                }
                e.target.value = value;
            });
        });
    },
    
    // Initialiser les tableaux
    initTables: function() {
        // Ajouter le tri aux tableaux
        const sortableTables = document.querySelectorAll('table[data-sortable="true"]');
        sortableTables.forEach(table => {
            const headers = table.querySelectorAll('th[data-sortable="true"]');
            headers.forEach(header => {
                header.style.cursor = 'pointer';
                header.addEventListener('click', () => {
                    this.sortTable(table, header.cellIndex);
                });
            });
        });
    },
    
    // Trier un tableau
    sortTable: function(table, columnIndex) {
        const tbody = table.querySelector('tbody');
        const rows = Array.from(tbody.querySelectorAll('tr'));
        const isAsc = table.getAttribute('data-sort-direction') !== 'asc';
        
        rows.sort((a, b) => {
            const aVal = a.cells[columnIndex].textContent.trim();
            const bVal = b.cells[columnIndex].textContent.trim();
            
            // Essayer de trier numériquement
            const aNum = parseFloat(aVal.replace(/[^\d.,]/g, '').replace(',', '.'));
            const bNum = parseFloat(bVal.replace(/[^\d.,]/g, '').replace(',', '.'));
            
            if (!isNaN(aNum) && !isNaN(bNum)) {
                return isAsc ? aNum - bNum : bNum - aNum;
            }
            
            // Sinon tri alphabétique
            return isAsc ? aVal.localeCompare(bVal) : bVal.localeCompare(aVal);
        });
        
        // Réorganiser les lignes
        rows.forEach(row => tbody.appendChild(row));
        
        // Mettre à jour l'indicateur de tri
        table.setAttribute('data-sort-direction', isAsc ? 'asc' : 'desc');
        
        // Mettre à jour les en-têtes
        const headers = table.querySelectorAll('th');
        headers.forEach((header, index) => {
            header.classList.remove('sort-asc', 'sort-desc');
            if (index === columnIndex) {
                header.classList.add(isAsc ? 'sort-asc' : 'sort-desc');
            }
        });
    },
    
    // Vérifier l'authentification
    checkAuth: function() {
        // Cette fonction vérifiera si l'utilisateur est connecté
        // Pour l'instant, c'est une simulation
        const authToken = localStorage.getItem('auth_token');
        this.state.isAuthenticated = !!authToken;
        
        if (this.state.isAuthenticated) {
            document.body.classList.add('user-authenticated');
        }
    }
}