package com.khue.calorietracker.onboarding.onboarding_presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.khue.calorietracker.core.designsystem.ui.theme.LocalSpacing

@Composable
fun UnitTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    unit: String,
    textStyle: TextStyle = TextStyle(
        fontSize = 70.sp,
        color = MaterialTheme.colorScheme.primary
    ),
) {
    val spacing = LocalSpacing.current
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = true) {
        focusRequester.requestFocus()
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        BasicTextField(
            value = TextFieldValue(value, selection = TextRange(value.length)),
            onValueChange = { onValueChange(it.text) },
            textStyle = textStyle,
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Number),
            modifier = Modifier
                .focusRequester(focusRequester)
                .width(IntrinsicSize.Max)
                .alignBy(LastBaseline)
        )
        Spacer(modifier = Modifier.width(spacing.spaceSmall))
        Text(text = unit, modifier = Modifier.alignBy(LastBaseline))
    }
}


@Preview(showBackground = true)
@Composable
fun UnitTextFieldPrev() {
    UnitTextField(value = "200", unit = "year", onValueChange = {})
}