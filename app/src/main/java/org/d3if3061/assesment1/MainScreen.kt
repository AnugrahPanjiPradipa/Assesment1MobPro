package org.d3if3061.assesment1

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.text.style.BackgroundColorSpan
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3061.assesment1.navigation.Screen
import org.d3if3061.assesment1.ui.theme.Assesment1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = stringResource(id = R.string.app_name),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = Color.Yellow,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.weight(0.7f))
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.hsl(329f, 0.72f, 0.31f),
                    titleContentColor = Color.Yellow,
                ),
                actions = {
                    IconButton(onClick = {navController.navigate(Screen.About.route)}) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_aplikasi),
                            tint = Color.Yellow
                        )
                    }
                }
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}


@SuppressLint("StringFormatMatches")
@Composable
fun ScreenContent(modifier: Modifier) {

    var berat by rememberSaveable {
        mutableStateOf("")
    }
    var beratError by rememberSaveable {
        mutableStateOf(false)
    }
    var tinggi by rememberSaveable {
        mutableStateOf("")
    }
    var tinggiError by rememberSaveable {
        mutableStateOf(false)
    }
    var umur by rememberSaveable {
        mutableStateOf("")
    }
    var umurError by rememberSaveable {
        mutableStateOf(false)
    }
    val radioOptions = listOf(
        stringResource(id = R.string.pria),
        stringResource(id = R.string.wanita)
    )
    var gender by rememberSaveable {
        mutableStateOf(radioOptions[0])
    }
    var bmr by rememberSaveable {
        mutableStateOf(0f)
    }
    var selectedActivityLevel by rememberSaveable {
        mutableStateOf(ActivityLevel.Jarang)
    }
    var dailyCalories by rememberSaveable {
        mutableStateOf(0f)
    }
    var activityImageResource by rememberSaveable {
        mutableStateOf(R.mipmap.rebahan)
    }
    var selectedActivityLevelWhenCalculate by rememberSaveable {
        mutableStateOf(ActivityLevel.Jarang)
    }
    var genderWhenCalculate by rememberSaveable {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = berat, onValueChange = {berat = it},
            label = { Text(text = stringResource(R.string.berat_badan))},
            isError = beratError,
            trailingIcon = { IconPicker(beratError, "kg")},
            supportingText = { ErrorHint(beratError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(value = tinggi, onValueChange = {tinggi = it},
            label = { Text(text = stringResource(R.string.Tinggi_badan))},
            isError = tinggiError,
            trailingIcon = { IconPicker(tinggiError, "cm")},
            supportingText = { ErrorHint(tinggiError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = umur,
            onValueChange = { umur = it },
            label = { Text(text = stringResource(id = R.string.Umur)) },
            isError = umurError,
            trailingIcon = {
                Box(
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    IconPicker(
                        umurError,
                        stringResource(id = R.string.tahun)
                    )
                }
            },
            supportingText = { ErrorHint(umurError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .padding(top = 0.5.dp)
        ) {
            GenderSwitch(
                isMaleSelected = gender == "Pria",
                onGenderSelected = { isMale -> gender = if (isMale) "Pria" else "Wanita" }
            )
        }
        ActivityLevelSelection(selectedActivityLevel) { level ->
            selectedActivityLevel = level
        }
        Button(onClick = {
            beratError = (berat == "" || berat == "0")
            tinggiError = (tinggi == "" || tinggi == "0")
            umurError = (umur == "" || umur == "00")
            if (beratError || tinggiError) return@Button
            val bmrValue = hitungBMR(
                if (gender == "Pria") Gender.MALE else Gender.FEMALE,
                berat.toFloat(),
                tinggi.toFloat(),
                umur.toInt()
            )
            if (bmrValue != 0f) {
                bmr = bmrValue
                selectedActivityLevelWhenCalculate = selectedActivityLevel // Perbarui level aktivitas yang dipilih
                dailyCalories = hitungKaloriHarian(bmrValue, selectedActivityLevel)
                activityImageResource = when (selectedActivityLevel) {
                    ActivityLevel.Jarang -> R.mipmap.rebahan
                    ActivityLevel.Ringan -> R.mipmap.jalans
                    ActivityLevel.Cukup -> R.mipmap.bersepeda
                    ActivityLevel.Tinggi -> R.mipmap.angkatbeban
                }
                genderWhenCalculate = gender
            }
        },
            modifier = Modifier.padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(Color.hsl(329f, 0.72f, 0.31f), contentColor = Color.Yellow),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(R.string.hitung))
        }
        if (bmr != 0f) {
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(
                text = stringResource(id = R.string.bmr_x, bmr),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = stringResource(id = R.string.kaloriharian, dailyCalories.toInt()),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(id = activityImageResource),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
            ResetButton(
                onReset = {
                    berat = ""
                    beratError = false
                    tinggi = ""
                    tinggiError = false
                    umur = ""
                    umurError = false
                    gender = radioOptions[0]
                    bmr = 0f
                    // Update selectedActivityLevel here
                    selectedActivityLevel = ActivityLevel.Jarang
                    dailyCalories = 0f
                },
                modifier = Modifier.padding(top = 8.dp)
            )
            Button(
                onClick = {
                    val message = context.getString(R.string.bagikan_template,
                        berat, tinggi, genderWhenCalculate, umur, selectedActivityLevel.name, bmr, dailyCalories)
                    shareData(context, message, selectedActivityLevel, genderWhenCalculate)
                },
                modifier = Modifier.padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(Color.hsl(329f, 0.72f, 0.31f), contentColor = Color.Yellow),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.bagikan))
            }
        }
    }
}

@Composable
fun ActivityLevelSelection(selectedLevel: ActivityLevel, onLevelSelected: (ActivityLevel) -> Unit) {
    Row(
        modifier = Modifier
            .padding(top = 6.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.activity_level),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            ActivityLevel.values().forEach { level ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { onLevelSelected(level) }
                        .padding(vertical = 8.dp)
                ) {
                    RadioButton(
                        colors = RadioButtonDefaults.colors(Color.hsl(329f, 0.72f, 0.31f)),
                        selected = selectedLevel == level,
                        onClick = null
                    )
                    Text(
                        text = stringResource(id = level.stringResId),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    }
                }
            }
        }
    }
@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.input_invalid))
    }
}

@Composable
fun GenderSwitch(isMaleSelected: Boolean, onGenderSelected: (Boolean) -> Unit) {
    val context = LocalContext.current
    val priaString = stringResource(id = R.string.pria)
    val wanitaString = stringResource(id = R.string.wanita)

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = if (isMaleSelected) priaString else wanitaString,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(end = 8.dp)
        )
        Switch(
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.hsl(329f, 0.72f, 0.31f),
                checkedTrackColor = Color.hsl(277f, 0.68f, 0.12f),
                uncheckedThumbColor = Color.hsl(329f, 0.72f, 0.31f),
                uncheckedTrackColor = Color.hsl(32f, 0.75f, 0.92f)
            ),
            checked = isMaleSelected,
            onCheckedChange = { onGenderSelected(it) }
        )
    }
}


@Composable
fun ResetButton(
    onReset: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onReset,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.hsl(329f, 0.72f, 0.31f)
        ),
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
    ) {
        Text(text = stringResource(R.string.reset), color = Color.Yellow)
    }
}
private fun hitungBMR(gender: Gender, berat: Float, tinggi: Float, umur: Int): Float {
    return when (gender) {
        Gender.MALE -> 66 + (13.7f * berat) + (5 * tinggi) - (6.8f * umur)
        Gender.FEMALE -> 655 + (9.6f * berat) + (1.8f * tinggi) - (4.7f * umur)
    }
}

private fun hitungKaloriHarian(bmr: Float, activityLevel: ActivityLevel): Float {
    return bmr * activityLevel.value
}

private fun shareData(context: Context, message: String, selectedActivityLevel: ActivityLevel, gender: String) {
    val activityLevelStringResId = when (selectedActivityLevel) {
        ActivityLevel.Jarang -> R.string.jarang
        ActivityLevel.Ringan -> R.string.ringan
        ActivityLevel.Cukup -> R.string.cukup
        ActivityLevel.Tinggi -> R.string.tinggi
    }
    val activityLevelString = context.getString(activityLevelStringResId)
    val genderStringResId = when (gender) {
        context.getString(R.string.pria) -> R.string.pria
        context.getString(R.string.wanita) -> R.string.wanita
        else -> R.string.pria
    }
    val genderString = context.getString(genderStringResId)
    val messageWithEnglishActivityLevel = message.replace(selectedActivityLevel.name, activityLevelString)
        .replace(gender, genderString)
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, messageWithEnglishActivityLevel)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}


enum class ActivityLevel(val value: Float, @StringRes val stringResId: Int) {
    Jarang(1.2f, R.string.jarang),
    Ringan(1.375f, R.string.ringan),
    Cukup(1.55f, R.string.cukup),
    Tinggi(1.9f, R.string.tinggi)
}

enum class Gender {
    MALE,
    FEMALE
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    Assesment1Theme {
        MainScreen(rememberNavController())
    }
}