package com.example.messenger.api.controllers

import com.example.messenger.api.models.User
import com.example.messenger.api.repositories.UserRepository
import com.example.messenger.api.services.UserServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import com.example.messenger.api.components.UserAssembler
import com.example.messenger.api.helpers.objects.UserVo
import com.example.messenger.api.helpers.objects.UserListVO

@RestController
@RequestMapping("/users")
class UserController (val userService: UserServiceImpl, val userAssembler: UserAssembler, val userRepository: UserRepository) {

    @PostMapping
    @RequestMapping("/registrations")

    fun create (@Validated @RequestBody userDetails: User): ResponseEntity<UserVo> {
        val user = userService.attemptRegistration(userDetails)
        return ResponseEntity.ok(userAssembler.toUserVO(user))
    }

    @GetMapping
    @RequestMapping("/details")
    fun echoDetails(request: HttpServletRequest): ResponseEntity<UserListVO> {
        val user = userRepository.findByUsername(request.userPrincipal.name) as User
        val users = userService.listUsers(user)

        return ResponseEntity.ok(userAssembler.toUserListVO(users))
    }

    @PutMapping
    fun update(@RequestBody updateDetails: User, request: HttpServletRequest): ResponseEntity<UserVo> {
        val currentUser = userRepository.findByUsername(request.userPrincipal.name)
        userService.updateUserStatus(currentUser as User, updateDetails)
        return ResponseEntity.ok(userAssembler.toUserVO(currentUser))
    }
}