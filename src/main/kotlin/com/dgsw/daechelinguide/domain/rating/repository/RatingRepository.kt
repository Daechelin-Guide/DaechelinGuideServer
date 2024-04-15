package com.dgsw.daechelinguide.domain.rating.repository

import com.dgsw.daechelinguide.domain.rating.domain.Rating
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RatingRepository: CrudRepository<Rating, Long>