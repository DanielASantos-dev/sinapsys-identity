package dev.mainconde.identity.controller

import dev.mainconde.identity.controller.mapper.UserMapper
import dev.mainconde.identity.controller.request.UserRequest
import dev.mainconde.identity.controller.response.ResponsePattern
import dev.mainconde.identity.controller.response.UserResponse
import dev.mainconde.identity.exceptions.PasswordInvalidException
import dev.mainconde.identity.exceptions.enums.Errors
import dev.mainconde.identity.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/user")
class UserController(
    private val userService: UserService,
    private val userMapper: UserMapper
    ) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@Valid @RequestBody userRequest: UserRequest):ResponsePattern<UserResponse>{
        if (userRequest.password != userRequest.passwordConfirmation){
            throw PasswordInvalidException(Errors.SI1000.message, Errors.SI1000.internalCode)
        }

        val userResponse = userMapper.toUserResponse(userService.createUser(userMapper.createUserEntity(userRequest)))

        return ResponsePattern<UserResponse>(
            result = userResponse,
            message = "User created successfully",
            success = true,
        )
    }

}