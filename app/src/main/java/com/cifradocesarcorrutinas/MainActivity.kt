package com.cifradocesarcorrutinas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textoOriginal = findViewById<EditText>(R.id.etTexto)
        val numero = findViewById<EditText>(R.id.etNumero)
        val botonEncriptar = findViewById<Button>(R.id.btnEncriptar)
        val textResultado = findViewById<TextView>(R.id.tvResultado)
        val botonDesencriptar = findViewById<Button>(R.id.btnDesencriptar)

        //al hacer click se calcula el resultado de la encriptacion con una corrutina
        botonEncriptar.setOnClickListener() {

            CoroutineScope(Dispatchers.Main).launch {
                val resultado = encriptador(
                    textoOriginal.text.toString().lowercase(),
                    numero.text.toString().toInt()
                )

                textResultado.text = resultado
            }

        }
        //al hacer click se calcula el resultado de la desencriptacion con una corrutina
        botonDesencriptar.setOnClickListener() {

            CoroutineScope(Dispatchers.Main).launch {
                val resultado = encriptador(
                    textoOriginal.text.toString().lowercase(), numero.text.toString().toInt() * -1
                )

                textResultado.text = resultado
            }

        }
    }

    // * Funcion que encripta o desencripta un texto, dependiendo del numero que se le pase,
    //si se pulsa el boton encriptar el numero introducido se pasa comom parametro
    //y se calculara la posicion de las letras a partir de el, pero si se pusla el boton desencriptar
    //se pasara el numero introducido multiplicado por -1, para que se calcule la posicion de las letras
    //lo que hace que se recorra el abecedario en sentido contrario
    private fun encriptador(texto: String, numero: Int): String {

        val numeroLimite = 27
        val abecedario = listOf(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        )

        var textoEncriptado = ""

        for (letra in texto) {
            when (letra) {
                ' ','.', ',','0', '1','2','3','4','5','6','7','8','9'-> textoEncriptado += letra
                else -> {
                    val posicion = abecedario.indexOf(letra)
                    val nuevaPosicion = (posicion + numero) % numeroLimite
                    textoEncriptado += abecedario[nuevaPosicion]
                }
            }
        }

        return textoEncriptado
    }


}