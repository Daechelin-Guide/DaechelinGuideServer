package com.dgsw.daechelinguide.domain.auth.repository

import com.dgsw.daechelinguide.domain.auth.entity.RefreshToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository: CrudRepository<RefreshToken, String>