# LendlyApp 🪙

Aplicación Android para **digitalizar el acceso a crédito, las compras y la administración financiera personal**: solicitud y gestión de préstamos, una tienda (*Shop*) integrada, historial de transacciones y perfil con puntaje crediticio.

> Trabajo práctico grupal (**Grupo 4 · ORT**) para la empresa ficticia **Software ORT**. Proyecto académico — no productivo.

---

## Tabla de contenidos

- [Funcionalidades](#-funcionalidades)
- [Stack técnico](#-stack-técnico)
- [Arquitectura](#-arquitectura)
- [Estructura del proyecto](#-estructura-del-proyecto)
- [Requisitos previos](#-requisitos-previos)
- [Configuración](#-configuración)
- [Cómo ejecutar](#-cómo-ejecutar)
- [Tests](#-tests)
- [API](#-api)
- [Manejo de sesión y seguridad](#-manejo-de-sesión-y-seguridad)
- [Convenciones de código](#-convenciones-de-código)
- [Flujo de Git](#-flujo-de-git)
- [Equipo](#-equipo)
- [Licencia](#-licencia)

---

## ✨ Funcionalidades

- **Autenticación**
  - Login con **teléfono + contraseña** contra la API mock.
  - **Registro** (Sign Up) con flujo guiado de 9 pasos (verificación de teléfono, OTP simulado, reconocimiento facial, verificación de ID, datos personales, firma y creación de contraseña).
  - **Verificación SMS simulada** (código contra valor mock, sin envío real).
  - **Persistencia de sesión**: el token se guarda cifrado; al reabrir la app, si hay sesión, va directo a Home.
  - **Logout** desde la sección *Manage* (borra el token persistido).
- **Navegación**
  - **Bottom Navigation Bar** con 5 tabs: **Home · Loans · Shop · History · Manage**, cada uno con su back stack.
  - **Splash** con redirección automática (Home si hay sesión, Onboarding si no).
- **Préstamos**: solicitud, listado de activos e historial, pantalla de éxito.
- **Shop**: catálogo de productos, búsqueda, filtros y detalle.
- **History**: historial de transacciones con detalle.
- **Manage**: perfil, puntaje crediticio y cierre de sesión.

---

## 🧱 Stack técnico

| Área | Tecnología |
|------|------------|
| Lenguaje | **Kotlin** `2.2.10` |
| UI | **Jetpack Compose** + **Material 3** (BOM `2024.09.00`) |
| Arquitectura | **MVVM** con **LiveData** |
| Navegación | **Navigation Compose** `2.8.5` |
| Inyección de dependencias | **Hilt** `2.59.2` |
| Networking | **Retrofit** `2.11.0` + **Moshi** `1.15.1` + OkHttp logging |
| Persistencia local | **Room** `2.7.2` |
| Sesión (token) | **EncryptedSharedPreferences** (`androidx.security:security-crypto` `1.1.0-alpha06`) |
| Imágenes | **Glide** `4.16.0` |
| Build | **AGP** `9.1.1` · **Gradle** `9.3.1` · **KSP** `2.2.10-2.0.2` |

**SDK:** `minSdk 24` · `target/compile SDK 36` · **Java target:** 11
**Package / applicationId:** `com.example.parcial_grupo_4`

> Las dependencias se declaran en el **version catalog** [`gradle/libs.versions.toml`](gradle/libs.versions.toml) y se referencian con `libs.*`.

---

## 🏗️ Arquitectura

Patrón **MVVM** en tres capas:

```
UI (Compose + ViewModels)  ──>  Repository  ──>  Data sources (API Retrofit / Room / SessionManager)
        LiveData                  dominio              DTOs ↔ modelos
```

- Las pantallas son `@Composable` con *state hoisting*; el estado mutable vive en el `ViewModel`.
- Los `ViewModel` exponen **`LiveData<T>`** (estado mutable interno, inmutable hacia afuera).
- Los **repositorios** combinan API + persistencia local y devuelven un `NetworkResult<T>` tipado (sin filtrar excepciones a la UI).
- **Hilt** provee `Retrofit`, DAOs, `SessionManager` y repos.

---

## 📁 Estructura del proyecto

```
com.example.parcial_grupo_4/
├── data/
│   ├── api/          # Retrofit services, DTOs, interceptores, NetworkResult, safeApiCall
│   ├── local/        # Room (DB, DAOs, entities) + SessionManager (token cifrado)
│   ├── repository/   # Repos que combinan api + local
│   └── model/        # Modelos de dominio + mappers
├── di/               # Módulos Hilt (Network, Api, Database)
├── ui/
│   ├── auth/         # Login, registro (flujo de 9 pantallas), ViewModels
│   ├── home/         # Home + Cash In + notificaciones
│   ├── loans/        # Préstamos
│   ├── shop/         # Tienda
│   ├── history/      # Historial de transacciones
│   ├── manage/       # Perfil, score, logout
│   ├── onboarding/   # Slides + entrada a Login / Sign Up
│   ├── splash/       # Splash con auto-login
│   ├── navigation/   # AppNavGraph (grafo raíz) + rutas
│   ├── common/       # Composables reutilizables (botones, top/bottom bar, etc.)
│   └── theme/        # Colores, tipografía, spacing, tema
└── util/             # Extensions, validators, helpers
```

---

## ✅ Requisitos previos

- **Android Studio** (versión reciente, compatible con AGP 9).
- **JDK 11** (o el JBR que trae Android Studio).
- **Android SDK 36** instalado.
- Un emulador o dispositivo físico con **API 24+**.

---

## ⚙️ Configuración

1. **Cloná el repo** y abrilo en Android Studio (`File → Open`).
2. Dejá que Gradle sincronice (descarga dependencias del version catalog).
3. **API key del mock**: se inyecta vía `BuildConfig.LOANS_API_KEY` desde una propiedad de Gradle. Hoy está definida en [`gradle.properties`](gradle.properties):

   ```properties
   LOANS_API_KEY=123456789
   ```

   > 🔐 **Buena práctica:** los secretos *reales* no deberían vivir en el control de versiones. Lo recomendado es ponerlos en `local.properties` (ignorado por git) o en variables de entorno y leerlos desde `build.gradle.kts`. En este proyecto la key es la del **mock público**, por lo que no representa un riesgo real.

4. `local.properties` (ruta del SDK, secretos locales) **no se commitea**.

---

## ▶️ Cómo ejecutar

Desde la raíz del proyecto (en **Windows/PowerShell** usá `.\gradlew` en lugar de `./gradlew`):

```bash
./gradlew assembleDebug        # compila el APK debug
./gradlew installDebug         # instala en el device/emulador conectado
./gradlew lint                 # análisis estático
```

O directamente con el botón **Run ▶** de Android Studio.

---

## 🧪 Tests

```bash
./gradlew test                 # unit tests (app/src/test, JUnit)
./gradlew connectedAndroidTest # tests instrumentados (app/src/androidTest, Compose/Espresso)
```

- **Unit tests** → `app/src/test/`
- **Tests instrumentados (UI)** → `app/src/androidTest/`

> Al sumar lógica nueva no trivial (validadores, mappers, repos) se acompaña con su test mínimo.

---

## 🌐 API

**Base URL (mock de Postman):**
`https://6d710e79-f4ca-4651-909f-7dd13bd29968.mock.pstmn.io`

Todas las llamadas pasan por **Retrofit** e incluyen el header `x-api-key`. Los errores de red se traducen a mensajes claros para el usuario (nunca stack traces crudos), vía `NetworkResult` + `safeApiCall`.

| Método | Path | Uso |
|--------|------|-----|
| `POST` | `/auth/login` | Login. Devuelve `token`. |
| `POST` | `/auth/create` | Registro de usuario. Devuelve `token`. |
| `GET`  | `/users/{id}` | Perfil del usuario autenticado. |
| `GET`  | `/loans` | Préstamos activos e historial. |
| `POST` | `/loans/apply` | Solicitud de nuevo préstamo. |
| `GET`  | `/transactions` | Historial de transacciones. |
| `GET`  | `/products` | Catálogo de productos del Shop. |

---

## 🔐 Manejo de sesión y seguridad

- El **token de autenticación** se persiste con **`EncryptedSharedPreferences`** sobre el **Android Keystore** (`SessionManager`). No queda en texto plano en disco (a diferencia de SQLite/Room sin cifrar).
- El token **nunca se loguea** ni se expone en la UI.
- El **Splash** consulta la sesión al iniciar y decide el destino (Home / Onboarding).
- El **logout** borra el token cifrado y vuelve al Onboarding.

---

## 🎨 Convenciones de código

- **Kotlin idiomático**: preferir `val`, *data classes*, *extension functions* cuando aporten claridad.
- **Compose**: `@Composable` en PascalCase, *state hoisting*, `remember`/`rememberSaveable` según corresponda.
- **ViewModels** exponen `LiveData<T>` (no `MutableLiveData` público).
- **Tema, colores, tipografía y medidas** viven en `ui/theme/` — no se hardcodean.
- **Strings de UI** en `res/values/strings.xml`.
- Comentar el *por qué*, no lo obvio.
- Estilo de código Kotlin **oficial** (`kotlin.code.style=official`).

---

## 🌱 Flujo de Git

- Mensajes de commit en **español**, en presente imperativo (ej: *"agrega pantalla de login"*).
- Rama principal: **`main`**. Trabajo en ramas `feature/*` o `fix/*`, integradas vía Pull Request.
- **No** se hace force push a `main`.
- Hay un placeholder de **pre-commit** ([`.pre-commit-config.yaml`](.pre-commit-config.yaml), `repos: []`) listo para sumar checks (ktlint, detekt, etc.) cuando el grupo lo decida.

---

## 👥 Equipo

**Grupo 4 — ORT.**

---

## 📄 Licencia

Proyecto académico de uso educativo. Sin licencia de distribución.

---

## 🗂️ Historial de cambios (preguntas y respuestas)



