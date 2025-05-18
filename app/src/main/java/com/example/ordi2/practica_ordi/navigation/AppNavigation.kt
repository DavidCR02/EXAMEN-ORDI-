package com.example.ordi2.practica_ordi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ordi2.practica_ordi.ui.screens.LoginScreen
import com.example.ordi2.practica_ordi.ui.screens.StudentDetailScreen
import com.example.ordi2.practica_ordi.ui.screens.StudentListScreen
import com.example.ordi2.practica_ordi.model.Student

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object StudentList : Screen("student_list")
    object StudentDetail : Screen("student_detail/{studentId}") {
        fun createRoute(studentId: String) = "student_detail/$studentId"
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    isLoggedIn: Boolean,
    onLoginSuccess: () -> Unit,
    onLogout: () -> Unit,
    students: List<Student>
) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) Screen.StudentList.route else Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                navController = navController,
                onLoginSuccess = onLoginSuccess
            )
        }

        composable(Screen.StudentList.route) {
            StudentListScreen(
                navController = navController,
                students = students,
                onStudentClick = { studentId ->
                    navController.navigate(Screen.StudentDetail.createRoute(studentId))
                },
                onLogout = onLogout
            )
        }

        composable(
            route = Screen.StudentDetail.route,
            arguments = listOf(
                navArgument("studentId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getString("studentId") ?: ""
            val student = students.find { it.id == studentId }
            student?.let {
                StudentDetailScreen(
                    navController = navController,
                    student = it
                )
            }
        }
    }
} 