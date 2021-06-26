package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.constraint

import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [NoNullElementsValidator::class])
@Target(allowedTargets = [FUNCTION, FIELD, ANNOTATION_CLASS, CONSTRUCTOR, VALUE_PARAMETER, TYPE_PARAMETER])
@Retention(AnnotationRetention.RUNTIME)
annotation class NoNullElements(
    val message: String = "must not contain null elements",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Payload>> = []
)

private class NoNullElementsValidator : ConstraintValidator<NoNullElements, Collection<Any>> {
    override fun isValid(value: Collection<Any>?, context: ConstraintValidatorContext): Boolean {
        // null values are valid
        if (value == null) {
            return true
        }
        return value.stream().noneMatch { it == null }
    }
}