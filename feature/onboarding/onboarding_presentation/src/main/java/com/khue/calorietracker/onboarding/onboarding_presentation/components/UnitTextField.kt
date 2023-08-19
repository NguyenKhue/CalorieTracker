package com.khue.calorietracker.onboarding.onboarding_presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UnitTextField(
    modifier: Modifier = Modifier,
    initValue: String,
    onValueChange: (String) -> Unit,
    transformation: ((String) -> String)? = null,
    unit: String,
    textStyle: TextStyle = TextStyle(
        fontSize = 70.sp,
        color = MaterialTheme.colorScheme.primary,
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Done,
        keyboardType = KeyboardType.Number
    ),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    focusRequester: FocusRequester = remember { FocusRequester() },
) {

    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = initValue,
                selection = TextRange(initValue.length)
            )
        )
    }

    Row(
        modifier = modifier.wrapContentWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        BasicTextField(
            value = textFieldValue,
            onValueChange = {
                val transformedText = transformation?.invoke(it.text) ?: it.text
                onValueChange(transformedText)
                textFieldValue = it.copy(text = transformedText)
            },
            textStyle = textStyle,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .focusRequester(focusRequester)
                .defaultMinSize(48.dp)
                .width(IntrinsicSize.Max)
                .weight(1f, fill = false)
                .alignBy(LastBaseline)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = unit, modifier = Modifier.alignBy(LastBaseline))
    }
}


@Preview(showBackground = true)
@Composable
fun UnitTextFieldPrev() {
    UnitTextField(initValue = "200", unit = "year", onValueChange = {})
}