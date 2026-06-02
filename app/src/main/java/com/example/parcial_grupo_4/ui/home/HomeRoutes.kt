package com.example.parcial_grupo_4.ui.home

sealed class HomeRoute(val route: String) {
    data object Home : HomeRoute("home_main")
    data object CashIn : HomeRoute("home_cash_in")
    data object OnlineCashIn : HomeRoute("home_online_cash_in")
    data object OverTheCounterCashIn : HomeRoute("home_over_the_counter_cash_in")
    data object CashInAmount : HomeRoute("home_cash_in_amount")
    data object SuccessfulTransaction : HomeRoute("home_successful_transaction")
    data object Notifications : HomeRoute("home_notifications")
}