```
╔════════════════════════════════════════════════════════════════════════════════╗
║                   BANKING SYSTEM - DOCKER ARCHITECTURE                        ║
╚════════════════════════════════════════════════════════════════════════════════╝

┌─────────────────────────────────────────────────────────────────────────────┐
│                            USER / BROWSER                                   │
│                          (http://localhost:4200)                            │
└────────────────────────────────┬────────────────────────────────────────────┘
                                 │
                    ┌────────────┴────────────┐
                    │                         │
        ┌───────────▼──────────────┐  ┌──────▼────────────────────┐
        │  FRONTEND CONTAINER      │  │                           │
        │  ┌────────────────────┐  │  │   HTTP REQUEST (Port 4200)│
        │  │  Nginx Server      │  │  │                           │
        │  │  ┌──────────────┐  │  │  │  - Angular App           │
        │  │  │Angular App   │  │  │  │  - Static Assets         │
        │  │  │  - Routes    │  │  │  │  - CSS/JS                │
        │  │  │  - Models    │  │  │  │                           │
        │  │  │  - Services  │  │  │  │                           │
        │  │  │  - Components│  │  │  │                           │
        │  │  └──────────────┘  │  │  │                           │
        │  │                    │  │  │                           │
        │  │  Proxy Rules:      │  │  │                           │
        │  │  /api/* → backend  │  │  │                           │
        │  │  /* → index.html   │  │  │                           │
        │  └────────────────────┘  │  │                           │
        └───────────────────────────┘  └──────────────────────────┘
                    │
                    │ API Calls (http://backend:8080/api)
                    │
        ┌───────────▼────────────────────────────┐
        │    BACKEND CONTAINER                   │
        │  ┌───────────────────────────────────┐ │
        │  │   Spring Boot 3.2.0                │ │
        │  │  ┌─────────────────────────────┐  │ │
        │  │  │  REST Controllers           │  │ │
        │  │  │  - BranchController         │  │ │
        │  │  │  - BankAccountController    │  │ │
        │  │  └─────────────────────────────┘  │ │
        │  │  ┌─────────────────────────────┐  │ │
        │  │  │  Services                   │  │ │
        │  │  │  - BranchService            │  │ │
        │  │  │  - BankAccountService       │  │ │
        │  │  └─────────────────────────────┘  │ │
        │  │  ┌─────────────────────────────┐  │ │
        │  │  │  Repositories               │  │ │
        │  │  │  - BranchRepository         │  │ │
        │  │  │  - BankAccountRepository    │  │ │
        │  │  └─────────────────────────────┘  │ │
        │  │  ┌─────────────────────────────┐  │ │
        │  │  │  Redis Cache Config         │  │ │
        │  │  │  @EnableCaching             │  │ │
        │  │  │  RedisTemplate              │  │ │
        │  │  └─────────────────────────────┘  │ │
        │  │                                   │ │
        │  │  Port: 8080                       │ │
        │  │  Depends on: mysql, redis         │ │
        │  │  Profile: docker                  │ │
        │  └───────────────────────────────────┘ │
        │           │              │              │
        │           │ JDBC         │ Redis       │
        │           │              │ Commands    │
        └───────────┼──────────────┼──────────────┘
                    │              │
        ┌───────────▼────┐  ┌──────▼──────────┐
        │ MYSQL CONTAINER│  │ REDIS CONTAINER │
        │ ┌────────────┐ │  │ ┌────────────┐  │
        │ │ MySQL 8.0  │ │  │ │ Redis 7.0  │  │
        │ │            │ │  │ │            │  │
        │ │ Database:  │ │  │ │ Port: 6379 │  │
        │ │banking_db  │ │  │ │            │  │
        │ │            │ │  │ │ Features:  │  │
        │ │ User:      │ │  │ │ - Caching  │  │
        │ │banking_user│ │  │ │ - Sessions │  │
        │ │            │ │  │ │ - AOF      │  │
        │ │ Port: 3306 │ │  │ │  Persist  │  │
        │ │            │ │  │ │            │  │
        │ │ Tables:    │ │  │ │ Volume:    │  │
        │ │ - branches │ │  │ │ redis_data │  │
        │ │ - accounts │ │  │ │            │  │
        │ │            │ │  │ │            │  │
        │ │ Volume:    │ │  │ │            │  │
        │ │ mysql_data │ │  │ │            │  │
        │ └────────────┘ │  │ └────────────┘  │
        └────────────────┘  └─────────────────┘

╔════════════════════════════════════════════════════════════════════════════════╗
║                          DOCKER NETWORK TOPOLOGY                              ║
╚════════════════════════════════════════════════════════════════════════════════╝

Network: banking-network (bridge)
  
  Services by hostname (internal):
  ├── mysql:3306         (MySQL Database)
  ├── redis:6379         (Redis Cache)
  ├── backend:8080       (Spring Boot API)
  └── frontend:80        (Nginx Server)

╔════════════════════════════════════════════════════════════════════════════════╗
║                            DATA FLOW DIAGRAM                                   ║
╚════════════════════════════════════════════════════════════════════════════════╝

User Browser Request
  │
  ├─→ GET /
  │   └─→ Nginx (frontend:80)
  │       └─→ Returns index.html + Angular app
  │
  ├─→ GET /api/branches
  │   └─→ Nginx (frontend:80)
  │       └─→ Proxy to http://backend:8080/api/branches
  │           └─→ Spring Boot Controller
  │               ├─→ Check Redis Cache (redis:6379)
  │               │   └─→ Cache hit? Return cached data
  │               │   └─→ Cache miss? Query database
  │               │
  │               ├─→ Query MySQL (mysql:3306)
  │               │   └─→ Execute SQL
  │               │       └─→ Return data
  │               │
  │               ├─→ Store in Redis (redis:6379)
  │               │   └─→ Cache for 10 minutes
  │               │
  │               └─→ Return JSON response
  │                   └─→ Nginx proxies to browser
  │
  └─→ User sees updated data

╔════════════════════════════════════════════════════════════════════════════════╗
║                          SERVICE DEPENDENCIES                                  ║
╚════════════════════════════════════════════════════════════════════════════════╝

Container Start Order:
1. mysql (No dependencies)
   └─→ Waits for health check: mysqladmin ping
   
2. redis (No dependencies)
   └─→ Waits for health check: redis-cli ping
   
3. backend (Depends on: mysql, redis)
   └─→ Waits for both above to be healthy
   └─→ Then starts Spring Boot application
   
4. frontend (Depends on: backend)
   └─→ Waits for backend to be healthy
   └─→ Then starts Nginx server

Total startup time: ~2-3 minutes

╔════════════════════════════════════════════════════════════════════════════════╗
║                          PORT MAPPING SUMMARY                                  ║
╚════════════════════════════════════════════════════════════════════════════════╝

Service    │ Container Port │ Host Port │ Access URL
───────────┼────────────────┼───────────┼──────────────────────────
Frontend   │ 80             │ 4200      │ http://localhost:4200
Backend    │ 8080           │ 8080      │ http://localhost:8080/api
MySQL      │ 3306           │ 3306      │ localhost:3306
Redis      │ 6379           │ 6379      │ localhost:6379

╔════════════════════════════════════════════════════════════════════════════════╗
║                          VOLUME PERSISTENCE                                    ║
╚════════════════════════════════════════════════════════════════════════════════╝

Volume        │ Location in Container    │ Purpose
──────────────┼─────────────────────────┼─────────────────────────
mysql_data    │ /var/lib/mysql          │ MySQL database files
redis_data    │ /data                   │ Redis persistence files

Persistence:
- Volumes survive container restart
- Can be backed up independently
- Can be restored independently
- Data deleted only when using: docker-compose down -v

╔════════════════════════════════════════════════════════════════════════════════╗
║                          HEALTH CHECK SUMMARY                                  ║
╚════════════════════════════════════════════════════════════════════════════════╝

Service   │ Check Method      │ Interval │ Timeout │ Retries
──────────┼──────────────────┼──────────┼─────────┼────────
mysql     │ mysqladmin ping   │ 5s       │ 20s     │ 10
redis     │ redis-cli ping    │ 3s       │ 10s     │ 5
backend   │ curl /api/branches│ 10s      │ 20s     │ 5
frontend  │ wget /            │ 10s      │ 10s     │ 5

Status codes:
- (healthy)   = All checks passing
- (starting)  = Still waiting for health check
- (unhealthy) = Failed health checks

╔════════════════════════════════════════════════════════════════════════════════╗
║                          QUICK REFERENCE COMMANDS                              ║
╚════════════════════════════════════════════════════════════════════════════════╝

Start:
  docker-compose up --build

Stop:
  docker-compose down

Status:
  docker-compose ps

Logs:
  docker-compose logs -f

Access shell:
  docker-compose exec backend /bin/bash
  docker-compose exec frontend /bin/sh
  docker-compose exec mysql bash
  docker-compose exec redis sh

Database:
  docker-compose exec mysql mysql -u banking_user -pbanking_password banking_db

Cache:
  docker-compose exec redis redis-cli
```

## Services Checklist

When `docker-compose up --build` completes, you should see:

```
✅ mysql is running
✅ redis is running
✅ backend is running
✅ frontend is running

All services healthy ✓
All checks passing ✓
```

## Next Steps

1. Start all services: `docker-compose up --build`
2. Open http://localhost:4200 in browser
3. Test APIs: `curl http://localhost:8080/api/branches`
4. Monitor: `docker-compose logs -f backend`
5. Scale: `docker-compose up --scale backend=3`
6. Deploy: Follow [DOCKER.md](DOCKER.md) for cloud deployment
