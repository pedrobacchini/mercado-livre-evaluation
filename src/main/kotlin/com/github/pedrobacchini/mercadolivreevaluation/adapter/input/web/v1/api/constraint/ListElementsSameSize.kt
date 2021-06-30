package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.constraint

import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [ListElementsSameSizeValidator::class])
@Target(allowedTargets = [FIELD])
@Retention(AnnotationRetention.RUNTIME)
annotation class ListElementsSameSize(
    val message: String = "elements at list must have same size",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Payload>> = []
)

private class ListElementsSameSizeValidator : ConstraintValidator<ListElementsSameSize, List<String?>> {

    override fun isValid(values: List<String?>, context: ConstraintValidatorContext): Boolean {
        val filterNotNull = values.filterNotNull()
        return filterNotNull.all { it.length == filterNotNull[0].length }
    }
}