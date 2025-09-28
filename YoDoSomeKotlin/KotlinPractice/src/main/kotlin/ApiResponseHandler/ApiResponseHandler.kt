package org.example.ApiResponseHandler

enum class ErrorType(val info: String){
    NETWORK("Error: Please check your internet connection."),
    SERVER("Error: Our servers are currently down. Please try again later."),
    UNAUTHORIZED("Error: You are not authorized.")
}

sealed class ApiResponse{
    data class Success(val message: String): ApiResponse()
    data class Failure(val errorType: ErrorType): ApiResponse()
    object Loading: ApiResponse()
}

fun handleResponse(response: ApiResponse): String{
    return when(response){
        is ApiResponse.Success -> response.message

        is ApiResponse.Failure -> {
            response.errorType.info
//            when(response.errorType){
//                ErrorType.NETWORK -> ErrorType.NETWORK.info
//                ErrorType.SERVER -> ErrorType.SERVER.info
//                ErrorType.UNAUTHORIZED -> ErrorType.UNAUTHORIZED.info
//            }
        }
        ApiResponse.Loading -> "Loading..."
    }
}

fun main(){
    val response = ApiResponse.Failure(ErrorType.UNAUTHORIZED)
    println(handleResponse(response))
}