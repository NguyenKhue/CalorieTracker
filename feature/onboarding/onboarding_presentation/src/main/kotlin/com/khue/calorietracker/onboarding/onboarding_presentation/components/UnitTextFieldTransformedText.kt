package com.khue.calorietracker.onboarding.onboarding_presentation.components

class WeightTransformation {

    private var mask = '.'

    // 3.11, 12.11, 111.11
    operator fun invoke(text: String): String {
        var out = ""

        var textTrimmed = text.filter { it.isDigit() || it == mask }.run {
            val firstMaskPosition = indexOfFirst { it == mask && indexOf(it) in 1..3 }
            filterIndexed { index, item -> item.isDigit() || index == firstMaskPosition }
        }

        val length = textTrimmed.length
        val firstAvailableMaskPosition = textTrimmed.indexOf(mask)

        val isMaskPositionAvailable = textTrimmed.indexOf(mask) in 1..3
        val isDouble = textTrimmed.toDoubleOrNull() != null

        if (isMaskPositionAvailable && isDouble) {
            out = if(length > firstAvailableMaskPosition + 3) textTrimmed.substring(0..firstAvailableMaskPosition + 2)
            else textTrimmed
        } else if(isDouble) {
            textTrimmed = textTrimmed.replace(mask.toString(), "")
            out = if(length > 3) {
                val firstPart = textTrimmed.substring(0..2)
                val secondPart = textTrimmed.substring(3 until length)
                "$firstPart.$secondPart"
            }
            else textTrimmed
        }

        return out
    }
}

