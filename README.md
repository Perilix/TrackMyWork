# ⏱️ TimeTracker

<div align="center">

![TimeTracker Logo](https://img.shields.io/badge/TimeTracker-v1.0-blue?style=for-the-badge&logo=clockify)

**Une application de suivi de temps moderne et élégante pour développeurs**

[![Symfony](https://img.shields.io/badge/Symfony-6.4-000000?style=flat-square&logo=symfony)](https://symfony.com)
[![Angular](https://img.shields.io/badge/Angular-17-DD0031?style=flat-square&logo=angular)](https://angular.io)
[![API Platform](https://img.shields.io/badge/API%20Platform-3.2-38A3A5?style=flat-square&logo=api)](https://api-platform.com)
[![PHP](https://img.shields.io/badge/PHP-8.2+-777BB4?style=flat-square&logo=php)](https://php.net)
[![TypeScript](https://img.shields.io/badge/TypeScript-5.0-3178C6?style=flat-square&logo=typescript)](https://www.typescriptlang.org)

![Demo](https://via.placeholder.com/800x400/0c0c0c/ffffff?text=TimeTracker+Demo)

</div>

## 🚀 Présentation

TimeTracker est une application web moderne de suivi de temps conçue pour les développeurs et freelances. Interface sombre élégante, temps réel, et analytics détaillés pour optimiser votre productivité.

### ✨ Fonctionnalités

- **⏰ Timer en temps réel** - Suivi précis du temps de travail
- **🎨 Interface moderne** - Design sombre avec glassmorphism
- **📊 Analytics détaillés** - Statistiques quotidiennes, hebdomadaires, mensuelles  
- **🎯 Gestion de projets** - Organisation par client et projet
- **📱 Responsive** - Interface adaptative mobile/desktop
- **🔄 Temps réel** - Synchronisation automatique des données
- **📈 Dashboard intuitif** - Vue d'ensemble de votre activité

## 🛠️ Stack Technique

### Backend
- **Symfony 6.4** - Framework PHP robuste
- **API Platform** - API REST automatisée + documentation
- **Doctrine ORM** - Gestion de base de données
- **SQLite** - Base de données embarquée (dev)
- **PostgreSQL** - Base de données production

### Frontend  
- **Angular 17** - Framework frontend moderne
- **TypeScript** - Langage typé pour plus de robustesse
- **Tailwind CSS** - Framework CSS utility-first
- **Angular Material** - Composants UI élégants
- **RxJS** - Programmation réactive

## 📁 Structure du Projet

```
timetracker/
├── 🔧 backend/          # API Symfony + API Platform
│   ├── src/
│   │   ├── Entity/      # Entités Doctrine
│   │   ├── Repository/  # Repositories
│   │   └── Controller/  # Controllers personnalisés
│   ├── config/          # Configuration Symfony
│   └── migrations/      # Migrations base de données
│
├── 🎨 frontend/         # Application Angular
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/  # Composants réutilisables
│   │   │   ├── services/    # Services API
│   │   │   ├── models/      # Modèles TypeScript
│   │   │   └── pages/       # Pages principales
│   │   └── assets/      # Ressources statiques
│   
└── 📚 docs/            # Documentation
```

## 🚀 Installation

### Prérequis

- PHP 8.2+
- Composer
- Symfony CLI
- Node.js 18+
- npm/yarn

### Backend (Symfony)

```bash
# Cloner le repository
git clone https://github.com/yourusername/timetracker.git
cd timetracker/backend

# Installer les dépendances
composer install

# Configuration de l'environnement
cp .env .env.local
# Éditer .env.local avec vos paramètres

# Créer la base de données
php bin/console doctrine:database:create
php bin/console doctrine:migrations:migrate

# (Optionnel) Charger les données de test
php bin/console doctrine:fixtures:load

# Démarrer le serveur de développement
symfony serve -d
```

L'API sera disponible sur `http://localhost:8000`  
Documentation interactive : `http://localhost:8000/api`

### Frontend (Angular)

```bash
# Aller dans le dossier frontend
cd ../frontend

# Installer les dépendances
npm install

# Démarrer le serveur de développement
ng serve
```

L'application sera disponible sur `http://localhost:4200`

## 🎯 Utilisation

### 1. **Créer un projet**
```http
POST /api/projects
{
  "name": "Refonte site e-commerce",
  "client": "TechCorp",
  "color": "#00d4ff"
}
```

### 2. **Démarrer un timer**
```http
POST /api/time_entries
{
  "project": "/api/projects/1",
  "startTime": "2025-01-15T09:00:00Z",
  "description": "Développement du panier"
}
```

### 3. **Consulter les statistiques**
```http
GET /api/stats/today
```

## 📊 API Endpoints

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| `GET` | `/api/projects` | Liste des projets |
| `POST` | `/api/projects` | Créer un projet |
| `GET` | `/api/time_entries` | Entrées de temps |
| `POST` | `/api/time_entries` | Démarrer un timer |
| `PATCH` | `/api/time_entries/{id}` | Arrêter/modifier timer |
| `GET` | `/api/stats/today` | Stats du jour |
| `GET` | `/api/stats/week` | Stats de la semaine |

> 📖 **Documentation complète** disponible sur `/api` une fois le serveur démarré

## 🎨 Screenshots

### Dashboard Principal
![Dashboard](https://via.placeholder.com/600x400/1a1a1a/ffffff?text=Dashboard+Principal)

### Gestion des Projets  
![Projets](https://via.placeholder.com/600x400/1a1a1a/ffffff?text=Gestion+Projets)

### Analytics & Rapports
![Analytics](https://via.placeholder.com/600x400/1a1a1a/ffffff?text=Analytics+%26+Stats)

## 🔧 Configuration

### Variables d'environnement (Backend)

```env
# .env.local
DATABASE_URL="sqlite:///%kernel.project_dir%/var/data.db"
APP_ENV=dev
APP_SECRET=your_secret_key
CORS_ALLOW_ORIGIN='^https?://(localhost|127\.0\.0\.1)(:[0-9]+)?$'
```

### Configuration Angular (Frontend)

```typescript
// src/environments/environment.ts
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8000/api'
};
```

## 🚀 Déploiement

### Backend (Symfony)
```bash
# Build pour la production
composer install --no-dev --optimize-autoloader
php bin/console cache:clear --env=prod
php bin/console doctrine:migrations:migrate --no-interaction
```

### Frontend (Angular)
```bash
# Build pour la production
ng build --configuration=production
```

### Docker (Optionnel)
```bash
# Construire et démarrer avec Docker Compose
docker-compose up -d --build
```

## 🧪 Tests

### Tests Backend
```bash
# Tests unitaires et fonctionnels
php bin/phpunit
```

### Tests Frontend
```bash
# Tests unitaires
ng test

# Tests e2e
ng e2e
```

## 📈 Roadmap

- [ ] **v1.1** - Export des données (CSV, PDF)
- [ ] **v1.2** - Notifications temps réel
- [ ] **v1.3** - Mode hors-ligne (PWA)
- [ ] **v1.4** - Intégrations (Slack, Discord)
- [ ] **v1.5** - Mobile app (Ionic)

## 🤝 Contribution

Les contributions sont les bienvenues ! 

1. Fork du projet
2. Créer une branche feature (`git checkout -b feature/amazing-feature`)
3. Commit des changements (`git commit -m 'Add amazing feature'`)
4. Push vers la branche (`git push origin feature/amazing-feature`)
5. Ouvrir une Pull Request

## 📝 Changelog

### v1.0.0 (2025-01-15)
- ✨ Interface utilisateur complète
- 🚀 API REST avec API Platform
- ⏰ Timer temps réel
- 📊 Dashboard analytics
- 🎨 Design responsive moderne

## 📄 License

Ce projet est sous licence MIT - voir le fichier [LICENSE](LICENSE) pour plus de détails.

## 💬 Support

- 📧 Email: [your.email@domain.com](mailto:your.email@domain.com)
- 🐛 Issues: [GitHub Issues](https://github.com/yourusername/timetracker/issues)
- 💬 Discussions: [GitHub Discussions](https://github.com/yourusername/timetracker/discussions)

---

<div align="center">

**⭐ N'oubliez pas de mettre une étoile si ce projet vous plaît ! ⭐**

Made with ❤️ and ☕ by [Your Name](https://github.com/yourusername)

![Footer](https://img.shields.io/badge/Happy-Coding-brightgreen?style=for-the-badge)

</div>
