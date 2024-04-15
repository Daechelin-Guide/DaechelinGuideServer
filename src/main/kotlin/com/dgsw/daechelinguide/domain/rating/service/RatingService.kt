package com.dgsw.daechelinguide.domain.rating.service

import com.dgsw.daechelinguide.domain.member.service.MemberService
import com.dgsw.daechelinguide.domain.menu.service.MenuService
import com.dgsw.daechelinguide.domain.rating.domain.Rating
import com.dgsw.daechelinguide.domain.rating.event.RatingScoreUpdateEvent
import com.dgsw.daechelinguide.domain.rating.presentation.dto.request.CreateRatingRequest
import com.dgsw.daechelinguide.domain.rating.presentation.dto.response.RatingResponse
import com.dgsw.daechelinguide.domain.rating.repository.RatingRepository
import com.dgsw.daechelinguide.domain.rating.repository.RatingRepositoryCustom
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class RatingService(
    private val ratingRepository: RatingRepository,
    private val ratingRepositoryCustom: RatingRepositoryCustom,
    private val memberService: MemberService,
    private val menuService: MenuService,
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    fun createRating(request: CreateRatingRequest, menuId: Long) {
        val member = memberService.getCurrentMember()
        val menu = menuService.getMenu(menuId)

        if(request.score > 5.0) {
            throw RuntimeException("시발")
        }

        val rating = Rating(
            score = request.score,
            comment = request.comment,
            member = member,
            menu = menu
        )

        ratingRepository.save(rating).also {
            applicationEventPublisher.publishEvent(RatingScoreUpdateEvent(it.menu, it.score))
        }
    }

    fun getRatingList(menuId: Long): List<RatingResponse> {
        val comment = ratingRepositoryCustom.findRatingCommentByMenuId(menuId)

        return comment.map {
            RatingResponse(it)
        }
    }
}