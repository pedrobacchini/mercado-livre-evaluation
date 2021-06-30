package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.api.constraint

import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [ListPatternValidator::class])
@Target(allowedTargets = [FIELD])
@Retention(AnnotationRetention.RUNTIME)
annotation class ListPattern(
    val message: String = "elements at list must match {regexp}",
    val regexp: String,
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Payload>> = []
)

private class ListPatternValidator : ConstraintValidator<ListPattern, List<String?>> {

    var pattern: String? = null

    override fun initialize(constraintAnnotation: ListPattern) {
        pattern = constraintAnnotation.regexp
    }

    override fun isValid(values: List<String?>, context: ConstraintValidatorContext): Boolean {
        val regex = pattern?.toRegex() ?: return false
        return values.filterNotNull().all { regex.matches(it) }
    }
}