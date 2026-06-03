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
- [Preguntas y respuestas](#-preguntas-y-respuestas)

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

## ❓ Preguntas y respuestas

### 1. ¿Qué tipo de arquitectura usaron y por qué? ¿Podría mejorarla?

Usamos **MVVM en capas con patrón Repository**, que es el requisito de la consigna y el estándar de Android. Las capas están bien separadas:

- **UI** (`ui/<feature>/`): Composables + ViewModels que exponen `LiveData`.
- **Repository** (`data/repository/`): orquesta las fuentes de datos.
- **Data sources**: Retrofit (`data/api/`) y Room (`data/local/`).
- **Dominio + mappers** (`data/model/`): modelos propios y conversiones DTO→Entity→UiModel.
- **DI** (`di/`): Hilt cablea todo.

El porqué: MVVM sobrevive a cambios de configuración (rotación), desacopla UI de lógica y hace testeable cada capa por separado. El Repository agrega una indirección clave: la UI no sabe si el dato vino de la red o de la base local.

La mejor parte de nuestra arquitectura es `TransactionRepository.kt:24-34`: implementamos **offline-first** a mano — la UI observa Room como fuente de verdad (`observeTransactions`) y `refresh()` trae de la red para actualizar el caché.

**¿Podría mejorarse?** La arquitectura es sólida pero **inconsistente entre features** (lo desarrollamos en la pregunta 3): Transactions está bien resuelto, mientras que Shop y Loans quedaron a mitad de camino. El siguiente paso natural sería sumar una capa de **UseCase/interactor** entre ViewModel y Repository; para el alcance del parcial lo consideramos sobre-ingeniería, así que no lo incorporamos.

### 2. ¿Tuvieron objetos stateful y stateless? ¿Cómo definen la elección?

El criterio que aplicamos es simple: ¿el objeto guarda y muta datos que sobreviven entre llamadas? → **stateful**. ¿Es una función/transformación sin memoria propia? → **stateless**. En el código se ve claro:

| Stateful (tienen estado) | Stateless (sin estado) |
|---|---|
| **ViewModels** — el estado de pantalla vive en sus `LiveData` (`LoansViewModel`: `amount`, `plan`, `purpose`, `applyState`) | **Retrofit services** (`AuthApi`, `TransactionApi`) — interfaces puras |
| **Room DB** (`LendlyDatabase`) — estado persistido en disco | **Mappers** (`toEntity`, `toUiModel`, `toDomain`) — funciones puras de transformación |
| **El token de sesión** persistido (hoy en `EncryptedSharedPreferences`) | **`safeApiCall`** — entra una llamada, sale un `NetworkResult` |
| **`ShopViewModel.originalShopData`** (`:27`) — caché mutable en memoria | **`toMessageRes()`** — mapeo puro error→string |

Concentramos el estado arriba (ViewModels y Room) y mantenemos stateless las capas de transformación para que sean predecibles y testeables. En Compose esto se llama **state hoisting**: los Composables son stateless y reciben el estado desde el ViewModel. Marcamos los repos como `@Singleton` justamente porque no guardan estado mutable propio — una sola instancia sirve para toda la app.

### 3. ¿Qué mejoras detectan que podrían realizarle a la app? ¿Tendrían side effect si escala el volumen de datos?

Detectamos varias mejoras; destacamos las más concretas:

**a) Búsqueda en memoria que no escala** — `ShopViewModel.kt:55-76`. `searchProducts` filtra la lista completa en RAM en cada tecla. Con un catálogo chico anda, pero si `/products` devuelve miles de items: cargamos todo en memoria y filtramos en el main thread en cada pulsación → *jank* y posible OOM. **Refactor:** búsqueda *server-side* (query param al endpoint) o paginación con Paging 3 (hoy no está en las deps) + filtrado en Room con índices.

**b) `replaceAll` borra y reinserta TODO el historial** — `TransactionRepository.kt:30`. En cada `refresh()` se vacía la tabla y se reinsertan todas las filas. Con historial grande es caro y, además, borra el caché aunque la red falle a mitad de camino. **Refactor:** `@Upsert` incremental por `id` + paginación, en vez de un *full replace*.


**Side effect transversal:** ningún endpoint pagina (`/loans`, `/transactions`, `/products` traen todo de una). Es el patrón de fondo a tener en cuenta si el volumen de datos crece.

### 4. ¿Qué manejo de errores harían? ¿Dónde los contemplan a nivel código? Estrategia de mapeo.

Ya tenemos la estrategia correcta implementada — el detalle es que no la aplicamos en todos lados. El patrón es un **mapeo en dos pasos**, que es justo la estrategia que más se adecúa:

1. **Capa de datos** → traduce la excepción técnica a un error de dominio, agnóstico de UI: `SafeApiCall.kt` captura `IOException`/`HttpException` y devuelve un `NetworkResult.Error(NetworkErrorType)` (`NetworkResult.kt`). Acá nunca se filtra un stack trace.
2. **Capa de presentación** → mapea ese enum a un texto localizable: `NetworkErrorType.toMessageRes()` (`NetworkErrorMessages.kt`) → `R.string.error_*`.

Por qué es la adecuada: separa "qué falló técnicamente" de "qué texto ve el usuario". El dato no conoce la UI (testeable, reusable) y el texto sale de `strings.xml` (traducible). Cumple la regla de la consigna de "mensajes claros, no stack traces crudos".

**Dónde está roto (deuda que reconocemos):** `ShopViewModel.kt:78-98` reimplementa el mapeo a mano, con strings en inglés hardcodeados en el ViewModel y sin usar `NetworkResult`. Lo mismo en `LoansViewModel.kt:59` (`"Error loading loans"`). **Refactor:** que `ProductRepository` y `LoanRepository` devuelvan `NetworkResult` (como ya hacen Auth y Transaction) y que los ViewModels usen `toMessageRes()`. Eso unifica el manejo de error y arregla la traducibilidad (pregunta 5).

Un detalle de seguridad: hacemos el mapeo sin volcar el mensaje de la excepción a la UI ni al log con datos sensibles — el patrón de `NetworkErrorType` ya lo garantiza porque solo expone la categoría, nunca el detalle técnico.

### 5. Si la tuviéramos que convertir a Español conservando el Inglés, ¿qué estrategia usarían? ¿Y para sumar otros idiomas?

Estado actual: solo existe `app/src/main/res/values/strings.xml` (221 líneas), no hay `values-es/`. El `values/` default está en inglés, así que hoy la app es monolingüe.

**Estrategia para ES + EN** (mecanismo nativo de Android, sin librerías):

- `values/strings.xml` (default) = inglés. Es el *fallback*: si el idioma del teléfono no matchea ningún recurso, cae acá. Así conservamos el inglés sin esfuerzo.
- `values-es/strings.xml` = traducción al español. Android resuelve el *locale* solo según la config del dispositivo — no tocamos código.
- (Opcional) `values-en/` explícito si queremos separarlo del default.

Pero hay un paso previo ineludible: hoy tenemos **strings hardcodeados en código** (los mensajes de error de `ShopViewModel`/`LoansViewModel`) que no se traducen nunca porque no pasan por `strings.xml`. Antes de internacionalizar hay que **externalizar todos los textos a recursos**; si no, traducimos a medias.

**Para sumar más idiomas** (portugués, etc.):

- Un directorio por idioma: `values-pt/`, `values-fr/`, con región si hace falta (`values-pt-rBR`). Nada más; el sistema de recursos escala solo.
- Usar **placeholders posicionales** `%1$s`, `%2$d` (no concatenar strings), porque el orden de las palabras cambia entre idiomas.
- Usar **plurals** (*quantity strings*) para cantidades ("1 préstamo" vs "3 préstamos").
- Ojo con **fechas y moneda**: en `TransactionMappers.kt:59-65` y `LoanRepository.kt:46-47` están formateadas con `Locale.US` hardcodeado. Para i18n real eso debería usar el `Locale` del dispositivo, si no las fechas/números quedan siempre en formato yanqui aunque la UI esté en español.



