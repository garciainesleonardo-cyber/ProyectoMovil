package com.example.projectmovil

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
// --------- ⬇️ IMPORTS NECESARIOS DE ROOM Y COROUTINES ⬇️ ---------
import com.example.projectmovil.data.database.AppDatabase
import com.example.projectmovil.data.dao.UsuarioDao
import com.example.projectmovil.data.entity.UsuarioEntity
import kotlinx.coroutines.*
// -------------------------------------------------------------------

class RegisterActivity : AppCompatActivity() {

    private var selectedObjective: String = ""
    private var selectedGender: String = ""

    private lateinit var usuarioDao: UsuarioDao
    private val scope = CoroutineScope(Dispatchers.Main + Job())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        usuarioDao = AppDatabase.getDatabase(applicationContext).usuarioDao()

        // --- Declaración de Vistas ---
        val etFirstName = findViewById<EditText>(R.id.et_first_name)
        val etLastName = findViewById<EditText>(R.id.et_last_name)
        val etEmail = findViewById<EditText>(R.id.et_email)
        val etPassword = findViewById<EditText>(R.id.et_password)
        val etPasswordConfirm = findViewById<EditText>(R.id.et_password_confirm)

        val btnObjective = findViewById<Button>(R.id.btn_objective)
        val tvObjectiveSummary = findViewById<TextView>(R.id.tv_objective_summary)

        val btnGender = findViewById<Button>(R.id.btn_gender)
        val tvGender = findViewById<TextView>(R.id.tv_gender)

        val etAge = findViewById<EditText>(R.id.et_age)
        val etHeightValue = findViewById<EditText>(R.id.et_height_value)
        val spHeightUnit = findViewById<Spinner>(R.id.sp_height_unit)
        val etWeightValue = findViewById<EditText>(R.id.et_weight_value)
        val spWeightUnit = findViewById<Spinner>(R.id.sp_weight_unit)
        val etTargetWeight = findViewById<EditText>(R.id.et_target_weight)

        val tvBmiResult = findViewById<TextView>(R.id.tv_bmi_result)
        val spActivityLevel = findViewById<Spinner>(R.id.sp_activity_level)

        val cbDiabetes = findViewById<CheckBox>(R.id.cb_diabetes)
        val cbEatingDisorder = findViewById<CheckBox>(R.id.cb_eating_disorder)
        val cbThyroid = findViewById<CheckBox>(R.id.cb_thyroid)
        val cbRenal = findViewById<CheckBox>(R.id.cb_renal)
        val cbDigestive = findViewById<CheckBox>(R.id.cb_digestive)
        val cbPregnancy = findViewById<CheckBox>(R.id.cb_pregnancy)
        val cbMedicalOther = findViewById<CheckBox>(R.id.cb_medical_other)
        val etMedicalOther = findViewById<EditText>(R.id.et_medical_other)

        val cbVegetarian = findViewById<CheckBox>(R.id.cb_vegetarian)
        val cbVegan = findViewById<CheckBox>(R.id.cb_vegan)
        val cbPescetarian = findViewById<CheckBox>(R.id.cb_pescetarian)
        val cbKeto = findViewById<CheckBox>(R.id.cb_keto)
        val cbPaleo = findViewById<CheckBox>(R.id.cb_paleo)
        val cbDietNone = findViewById<CheckBox>(R.id.cb_none)

        val cbGluten = findViewById<CheckBox>(R.id.cb_gluten)
        val cbLactose = findViewById<CheckBox>(R.id.cb_lactose)
        val cbNuts = findViewById<CheckBox>(R.id.cb_nuts)
        val cbShellfish = findViewById<CheckBox>(R.id.cb_shellfish)
        val cbAllergyOther = findViewById<CheckBox>(R.id.cb_allergy_other)
        val etAllergyOther = findViewById<EditText>(R.id.et_allergy_other)

        val etDislikedFoods = findViewById<EditText>(R.id.et_disliked_foods)

        val btnRegister = findViewById<Button>(R.id.btn_register)
        val btnCancel = findViewById<Button>(R.id.btn_cancel)

        // --- Inicialización y Listeners ---
        etMedicalOther.isEnabled = false
        etAllergyOther.isEnabled = false

        btnObjective.setOnClickListener {
            val options = arrayOf("Perder peso", "Mantener peso", "Ganar masa muscular")
            AlertDialog.Builder(this)
                .setTitle("Selecciona tu objetivo")
                .setItems(options) { _, which ->
                    selectedObjective = options[which]
                    tvObjectiveSummary.text = selectedObjective
                }
                .show()
        }

        btnGender.setOnClickListener {
            val options = arrayOf("Hombre", "Mujer", "Otro")
            AlertDialog.Builder(this)
                .setTitle("Selecciona tu género")
                .setItems(options) { _, which ->
                    selectedGender = options[which]
                    tvGender.text = selectedGender
                }
                .show()
        }

        val unitsHeight = arrayOf("cm", "ft")
        spHeightUnit.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, unitsHeight)

        val unitsWeight = arrayOf("kg", "lb")
        spWeightUnit.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, unitsWeight)

        val activityLevels = arrayOf(
            "Seleccionar nivel de actividad",
            "Sedentario (poco o ningún ejercicio)",
            "Ligero (ejercicio 1-3 días/semana)",
            "Moderado (ejercicio 3-5 días/semana)",
            "Activo (ejercicio 6-7 días/semana)",
            "Muy activo (ejercicio intenso diario)"
        )
        spActivityLevel.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, activityLevels)

        // Listener para calcular IMC automáticamente
        val bmiCalculator = {
            calculateAndDisplayBMI(
                etHeightValue.text.toString(),
                spHeightUnit.selectedItem?.toString() ?: "cm",
                etWeightValue.text.toString(),
                spWeightUnit.selectedItem?.toString() ?: "kg",
                tvBmiResult
            )
        }

        // Calcular IMC cuando cambia la altura
        etHeightValue.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { bmiCalculator() }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Calcular IMC cuando cambia el peso
        etWeightValue.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { bmiCalculator() }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Calcular IMC cuando cambia la unidad de altura
        spHeightUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                bmiCalculator()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Calcular IMC cuando cambia la unidad de peso
        spWeightUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                bmiCalculator()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        cbMedicalOther.setOnCheckedChangeListener { _, checked ->
            etMedicalOther.isEnabled = checked
            if (!checked) etMedicalOther.setText("")
        }

        cbAllergyOther.setOnCheckedChangeListener { _, checked ->
            etAllergyOther.isEnabled = checked
            if (!checked) etAllergyOther.setText("")
        }

        val dietCheckboxes = listOf(cbVegetarian, cbVegan, cbPescetarian, cbKeto, cbPaleo, cbDietNone)
        dietCheckboxes.forEach { checkbox ->
            checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked && buttonView == cbDietNone) {
                    dietCheckboxes.filter { it != cbDietNone }.forEach { it.isChecked = false }
                } else if (isChecked && buttonView != cbDietNone) {
                    cbDietNone.isChecked = false
                }
            }
        }

        btnCancel.setOnClickListener {
            finish()
        }
        // --- Fin de Inicialización y Listeners ---


        // *******************************************************************
        // LÓGICA DE REGISTRO CON ROOM
        // *******************************************************************
        btnRegister.setOnClickListener {
            // --- INICIO DE VALIDACIONES ---
            if (etFirstName.text.isBlank()) {
                Toast.makeText(this, "Por favor ingresa tu nombre", Toast.LENGTH_SHORT).show()
                etFirstName.requestFocus()
                return@setOnClickListener
            }

            if (etLastName.text.isBlank()) {
                Toast.makeText(this, "Por favor ingresa tus apellidos", Toast.LENGTH_SHORT).show()
                etLastName.requestFocus()
                return@setOnClickListener
            }

            val email = etEmail.text.toString().trim()
            if (email.isBlank()) {
                Toast.makeText(this, "Por favor ingresa tu correo", Toast.LENGTH_SHORT).show()
                etEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Por favor ingresa un correo válido", Toast.LENGTH_SHORT).show()
                etEmail.requestFocus()
                return@setOnClickListener
            }

            val password = etPassword.text.toString()
            if (password.isBlank()) {
                Toast.makeText(this, "Por favor ingresa una contraseña", Toast.LENGTH_SHORT).show()
                etPassword.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                etPassword.requestFocus()
                return@setOnClickListener
            }

            if (password != etPasswordConfirm.text.toString()) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                etPasswordConfirm.requestFocus()
                return@setOnClickListener
            }

            if (selectedObjective.isEmpty()) {
                Toast.makeText(this, "Por favor selecciona tu objetivo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (selectedGender.isEmpty()) {
                Toast.makeText(this, "Por favor selecciona tu género", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val age = etAge.text.toString().toIntOrNull()
            if (age == null || age < 13 || age > 120) {
                Toast.makeText(this, "Por favor ingresa una edad válida (13-120)", Toast.LENGTH_SHORT).show()
                etAge.requestFocus()
                return@setOnClickListener
            }

            val height = etHeightValue.text.toString().toDoubleOrNull()
            if (height == null || height <= 0) {
                Toast.makeText(this, "Por favor ingresa una altura válida", Toast.LENGTH_SHORT).show()
                etHeightValue.requestFocus()
                return@setOnClickListener
            }

            val weight = etWeightValue.text.toString().toDoubleOrNull()
            if (weight == null || weight <= 0) {
                Toast.makeText(this, "Por favor ingresa un peso válido", Toast.LENGTH_SHORT).show()
                etWeightValue.requestFocus()
                return@setOnClickListener
            }

            val targetWeight = etTargetWeight.text.toString().toDoubleOrNull()
            if (targetWeight == null || targetWeight <= 0) {
                Toast.makeText(this, "Por favor ingresa un peso objetivo válido", Toast.LENGTH_SHORT).show()
                etTargetWeight.requestFocus()
                return@setOnClickListener
            }

            if (spActivityLevel.selectedItemPosition == 0) {
                Toast.makeText(this, "Por favor selecciona tu nivel de actividad", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // --- FIN DE VALIDACIONES ---

            // Calcular IMC final
            val bmi = calculateBMI(height!!, spHeightUnit.selectedItem.toString(), weight!!, spWeightUnit.selectedItem.toString())

            // Mapeo de datos complejos a CSV
            val medicalConditionsStr = getMedicalConditions(cbDiabetes, cbEatingDisorder, cbThyroid, cbRenal, cbDigestive, cbPregnancy, cbMedicalOther, etMedicalOther).joinToString(separator = ",")
            val dietaryPreferencesStr = getDietaryPreferences(cbVegetarian, cbVegan, cbPescetarian, cbKeto, cbPaleo, cbDietNone).joinToString(separator = ",")
            val allergiesStr = getAllergies(cbGluten, cbLactose, cbNuts, cbShellfish, cbAllergyOther, etAllergyOther).joinToString(separator = ",")

            // Crear el objeto UsuarioEntity
            val nuevoUsuario = UsuarioEntity(
                nombre = etFirstName.text.toString().trim(),
                apellido = etLastName.text.toString().trim(),
                email = email,
                contrasena = password,
                objetivo = selectedObjective,
                genero = selectedGender,
                edad = age!!,
                altura = height,
                unidadAltura = spHeightUnit.selectedItem.toString(),
                pesoActual = weight,
                unidadPeso = spWeightUnit.selectedItem.toString(),
                pesoObjetivo = targetWeight,
                imc = bmi,
                nivelActividad = spActivityLevel.selectedItem.toString(),
                condicionesMedicas = medicalConditionsStr,
                preferenciasDieteticas = dietaryPreferencesStr,
                alergias = allergiesStr,
                comidasNoDeseadas = etDislikedFoods.text.toString().trim()
            )

            // Ejecutar la inserción en la base de datos de forma asíncrona
            scope.launch(Dispatchers.IO) {

                // Opcional: Verificar si el email ya existe antes de registrar (usando el login)
                val existingUser = usuarioDao.login(email, password)

                if (existingUser != null) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@RegisterActivity, "ERROR: El correo ya está registrado. Intenta iniciar sesión.", Toast.LENGTH_LONG).show()
                    }
                } else {
                    usuarioDao.registrar(nuevoUsuario) // Insertar en Room

                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@RegisterActivity, "Registro completado exitosamente ✓", Toast.LENGTH_LONG).show()
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    private fun calculateBMI(height: Double, heightUnit: String, weight: Double, weightUnit: String): Double {
        // Convertir a metros y kilogramos
        val heightInMeters = if (heightUnit == "ft") {
            height * 0.3048 // pies a metros
        } else {
            height / 100.0 // cm a metros
        }

        val weightInKg = if (weightUnit == "lb") {
            weight * 0.453592 // libras a kg
        } else {
            weight
        }

        // Fórmula IMC: peso(kg) / altura(m)²
        return if (heightInMeters > 0) {
            weightInKg / (heightInMeters * heightInMeters)
        } else {
            0.0
        }
    }

    private fun getBMICategory(bmi: Double): Pair<String, Int> {
        return when {
            bmi < 18.5 -> Pair("Bajo peso", 0xFFFF9800.toInt()) // Naranja
            bmi < 25.0 -> Pair("Peso normal", 0xFF4CAF50.toInt()) // Verde
            bmi < 30.0 -> Pair("Sobrepeso", 0xFFFF9800.toInt()) // Naranja
            else -> Pair("Obesidad", 0xFFF44336.toInt()) // Rojo
        }
    }

    private fun calculateAndDisplayBMI(
        heightStr: String,
        heightUnit: String,
        weightStr: String,
        weightUnit: String,
        tvBmiResult: TextView
    ) {
        val height = heightStr.toDoubleOrNull()
        val weight = weightStr.toDoubleOrNull()

        if (height != null && weight != null && height > 0 && weight > 0) {
            val bmi = calculateBMI(height, heightUnit, weight, weightUnit)
            val (category, color) = getBMICategory(bmi)

            tvBmiResult.text = String.format("IMC: %.1f - %s", bmi, category)
            tvBmiResult.setTextColor(color)
        } else {
            tvBmiResult.text = "IMC: -- (Ingresa peso y altura)"
            tvBmiResult.setTextColor(0xFF999999.toInt())
        }
    }

    private fun getMedicalConditions(
        cbDiabetes: CheckBox, cbEatingDisorder: CheckBox, cbThyroid: CheckBox,
        cbRenal: CheckBox, cbDigestive: CheckBox, cbPregnancy: CheckBox,
        cbMedicalOther: CheckBox, etMedicalOther: EditText
    ): List<String> {
        val conditions = mutableListOf<String>()
        if (cbDiabetes.isChecked) conditions.add("Diabetes")
        if (cbEatingDisorder.isChecked) conditions.add("Trastorno alimenticio")
        if (cbThyroid.isChecked) conditions.add("Problemas tiroideos")
        if (cbRenal.isChecked) conditions.add("Problemas renales/hepáticos")
        if (cbDigestive.isChecked) conditions.add("Enfermedades digestivas")
        if (cbPregnancy.isChecked) conditions.add("Embarazo o lactancia")
        if (cbMedicalOther.isChecked && etMedicalOther.text.isNotBlank()) {
            conditions.add(etMedicalOther.text.toString().trim())
        }
        return conditions
    }

    private fun getDietaryPreferences(
        cbVegetarian: CheckBox, cbVegan: CheckBox, cbPescetarian: CheckBox,
        cbKeto: CheckBox, cbPaleo: CheckBox, cbDietNone: CheckBox
    ): List<String> {
        val preferences = mutableListOf<String>()
        if (cbVegetarian.isChecked) preferences.add("Vegetariano")
        if (cbVegan.isChecked) preferences.add("Vegano")
        if (cbPescetarian.isChecked) preferences.add("Pescetariano")
        if (cbKeto.isChecked) preferences.add("Keto")
        if (cbPaleo.isChecked) preferences.add("Paleo")
        if (cbDietNone.isChecked) preferences.add("Ninguna")
        return preferences
    }

    private fun getAllergies(
        cbGluten: CheckBox, cbLactose: CheckBox, cbNuts: CheckBox,
        cbShellfish: CheckBox, cbAllergyOther: CheckBox, etAllergyOther: EditText
    ): List<String> {
        val allergies = mutableListOf<String>()
        if (cbGluten.isChecked) allergies.add("Gluten/Celíaca")
        if (cbLactose.isChecked) allergies.add("Lactosa")
        if (cbNuts.isChecked) allergies.add("Frutos secos")
        if (cbShellfish.isChecked) allergies.add("Mariscos")
        if (cbAllergyOther.isChecked && etAllergyOther.text.isNotBlank()) {
            allergies.add(etAllergyOther.text.toString().trim())
        }
        return allergies
    }
}