package dev.mainconde.identity.exceptions.controller

import dev.mainconde.identity.exceptions.BadRequestException
import dev.mainconde.identity.exceptions.InternalServerError
import dev.mainconde.identity.exceptions.NotFoundException
import dev.mainconde.identity.exceptions.PasswordInvalidException
import dev.mainconde.identity.exceptions.enums.Errors
import dev.mainconde.identity.exceptions.response.ErrorResponse
import dev.mainconde.identity.exceptions.response.FieldErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(BadRequestException::class)
    fun handlerBadRequestException(badRequestException: BadRequestException, request: WebRequest):ResponseEntity<ErrorResponse>{
        val error = ErrorResponse(
            badRequestException.message,
            badRequestException.code,
            null
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        ex: MethodArgumentNotValidException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            Errors.SI001.message,
            Errors.SI001.internalCode,
            ex.bindingResult.fieldErrors.map { FieldErrorResponse(it.defaultMessage ?: "Invalid", it.field) }
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handlerNotFoundException(ex: NotFoundException, webRequest: WebRequest): ResponseEntity<ErrorResponse>{
        val error = ErrorResponse(
            ex.message,
            ex.code,
            null
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(InternalServerError::class)
    fun handlerInternalServerError(ex: InternalServerError, webRequest: WebRequest): ResponseEntity<ErrorResponse>{
        val error = ErrorResponse(
            ex.message,
            ex.code,
        null
        )
        return ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(PasswordInvalidException::class)
    fun handlePasswordInvalidException(ex: PasswordInvalidException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            ex.message,
            ex.errorCode,
            null
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
}