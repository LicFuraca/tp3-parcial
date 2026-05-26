# CLAUDE.md

Instrucciones para Claude Code en este proyecto.

## Sobre el proyecto: LendlyApp

App Android **LendlyApp** para la empresa **Software ORT**. Objetivo: digitalizar el acceso a crédito, compras y administración financiera personal.

Funcionalidades clave:
- Solicitar y gestionar préstamos
- Compras en el **Shop** integrado
- Historial de transacciones
- Perfil y puntaje crediticio
- Diseño basado en un Figma provisto por la empresa

Es un parcial grupal (grupo 4) de ORT.

## Stack técnico (requerido por la consigna)

- **Lenguaje:** Kotlin
- **Arquitectura:** MVVM con **LiveData**
- **Networking:** **Retrofit**
- **Navegación:** **Navigation Component** (no compose-navigation)
- **DI:** **Hilt**
- **UI:** **Material 3** con **Jetpack Compose**
- **Imágenes:** **Glide**
- **Persistencia local:** **Room**
- **Min SDK:** 24 — **Target/Compile SDK:** 36
- **Java target:** 11
- **Package:** `com.example.parcial_grupo_4`
- **Dependencias:** Version catalog en `gradle/libs.versions.toml` (siempre usar `libs.*`)

> Importante: el stack mezcla Compose con Navigation Component clásico + LiveData. Es **a propósito** (requisito del parcial). No proponer reemplazarlos por StateFlow / compose-navigation salvo que se pida.

## API

**Base URL:** `https://6d710e79-f4ca-4651-909f-7dd13bd29968.mock.pstmn.io`

Endpoints:
| Método | Path             | Uso                                     |
|--------|------------------|-----------------------------------------|
| POST   | `/auth/login`    | Login. Retorna token.                   |
| POST   | `/auth/create`   | Registro de usuario.                    |
| GET    | `/users/{id}`    | Perfil del usuario autenticado.         |
| GET    | `/loans`         | Préstamos activos e historial.          |
| POST   | `/loans/apply`   | Solicitud de nuevo préstamo.            |
| GET    | `/transactions`  | Historial de transacciones.             |
| GET    | `/products`      | Catálogo de productos del Shop.         |

Reglas de integración:
- Toda llamada va por Retrofit.
- Manejar errores de red con mensajes claros al usuario (no stack traces crudos).
- El token de auth se persiste localmente (DataStore o SharedPreferences encriptado).

## Funcionalidades obligatorias

### Autenticación
- Login con **teléfono + contraseña**.
- Registro con validación de campos y verificación por SMS.
- **Persistencia de sesión**: si hay token guardado, no volver al Login al relanzar.
- **Logout** disponible desde la sección Manager.

### Navegación
- **Bottom Navigation Bar** con 5 tabs: **Home, Loans, Shop, History, Manage**.
- Cada tab tiene su propio back stack interno.
- **Splash**: redirige a Home si hay sesión, a Onboarding si no.

### UX
- Validación de formularios con **feedback visual** y mensajes de error.
- **Pantallas de éxito** tras: solicitar préstamo, hacer Cash In, actualizar perfil.

## Estructura sugerida (MVVM)

```
com.example.parcial_grupo_4/
├── data/
│   ├── api/          # Retrofit services, DTOs, interceptors
│   ├── local/        # Room DB, DAOs, entities
│   ├── repository/   # Repos que combinan api + local
│   └── model/        # Modelos de dominio
├── di/               # Módulos Hilt
├── ui/
│   ├── auth/         # Login, Register, SMS verification
│   ├── home/
│   ├── loans/
│   ├── shop/
│   ├── history/
│   ├── manage/       # Perfil, logout
│   ├── onboarding/
│   ├── splash/
│   ├── common/       # Composables reutilizables
│   └── theme/        # Colors, Typography, Theme
└── util/             # Extensions, validators, helpers
```

Cada feature: `XScreen.kt` (Composable) + `XViewModel.kt` (hereda `ViewModel`, expone `LiveData`).

## Comunicación

- Respondé siempre en **español rioplatense** (vos, no tú).
- Sé conciso. Nada de resúmenes largos al final de cada respuesta.
- Si una acción no es trivial (instalar deps, borrar archivos, push, refactors grandes), confirmá antes.

## Convenciones de código

- **Kotlin idiomático:** preferí `val` sobre `var`, data classes, extension functions cuando aporten claridad.
- **Compose:** funciones `@Composable` en PascalCase. State hoisting. `remember`/`rememberSaveable` cuando corresponda.
- **ViewModels** exponen `LiveData<T>` (no `MutableLiveData` público). Estado mutable interno.
- **Theme/colores/tipografía:** vivir en `ui/theme/`. No hardcodear colores ni medidas.
- **Strings de UI:** ir a `res/values/strings.xml`, no hardcodear.
- **Sin comentarios obvios.** Solo comentar el *por qué* cuando no se deduce del código.

## Dependencias

- **No agregar librerías nuevas sin avisar.** Si hace falta una, proponela primero con justificación.
- Declarar en `gradle/libs.versions.toml`, referenciar con `libs.*`.
- Dependencias previstas por la consigna: Retrofit, Hilt, Navigation Component, Material 3 Compose, Glide, Room, LiveData (lifecycle).

## Comandos útiles

```powershell
.\gradlew assembleDebug        # build debug
.\gradlew installDebug         # instalar en device/emu
.\gradlew test                 # unit tests
.\gradlew connectedAndroidTest # tests instrumentados
.\gradlew lint                 # lint
```

## Git

- **Mensajes de commit en español**, presente, imperativo (ej: "agrega pantalla de login").
- **No pushear sin pedir.** Confirmar siempre antes de `git push`.
- **No force push** a `main`.
- Rama principal: `main`.

## Qué NO tocar sin permiso

- `app/src/main/AndroidManifest.xml` (permisos, intents) — avisar si hay que agregar permisos (INTERNET, etc.)
- `app/build.gradle.kts` (versiones, plugins) salvo para agregar deps ya conversadas
- `gradle/wrapper/` (versión de Gradle)
- `local.properties`, keystores, `.env`

## Tests

- Unit tests: `app/src/test/` (JUnit).
- Tests instrumentados (UI/Espresso/Compose): `app/src/androidTest/`.
- Si agregás lógica nueva no trivial (validadores, mappers, repos), sumá un test mínimo.

## Estilo de trabajo

- Si una tarea es ambigua, **preguntá antes de asumir**.
- Si encontrás un bug colateral mientras trabajás en otra cosa, **avisame** en vez de arreglarlo de paso.
- Antes de marcar algo como terminado, asegurate que compila (`.\gradlew assembleDebug`).
- Cuando armes una feature nueva, seguí la estructura de carpetas de arriba.
