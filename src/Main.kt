fun main() {
    var input: String?
    do {
        print("""
            Silahkan pilih menu yang ada dibawah ini:
            1. Hello world 
            2. Cek format email
            3. Konversi waktu
            4. reverse text
            5. Cek palindrom
            
        """.trimIndent())
        print("Pilih: ")
        input = readLine()

        when (input) {
            "1" -> {
                println("=================")
                print("Masukan angka: ")
                val x = readLine()?.toInt()
                println("Result: ${x?.Q1()}")
                print("Apakah anda ingin kembali [y/n]: ")
                input = readLine()
                println("=================")
            }

            "2" -> {
                println("=================")
                print("Masukan email: ")
                val x = readLine()

                when (x?.Q2()) {
                    200 -> {
                        println("Result: Email telah valid!")
                    }

                    401 -> {
                        println("Result: tidak terdapat @ pada email!")
                    }

                    402 -> {
                        println("Result: tidak boleh melebih 20 kata sebelum @!")
                    }

                    403 -> {
                        println("Result: tidak terdapat titik setelah @!")
                    }

                    404 -> {
                        println("Result: domain tidak sesuai!")
                    }
                }

                print("Apakah anda ingin kembali [y/n]: ")
                input = readLine()
                println("=================")
            }

            "3" -> {
                println("=================")
                print("Masukan waktu dengan format[HH:mm:ss PM/AM]: ")
                val x = readLine()
                println("Result: ${x?.Q3()}")
                print("Apakah anda ingin kembali [y/n]: ")
                input = readLine()
                println("=================")
            }

            "4" -> {
                println("=================")
                print("Masukan text: ")
                val x = readLine()
                println("Result: ${x?.Q4()}")
                print("Apakah anda ingin kembali [y/n]: ")
                input = readLine()
                println("=================")
            }

            "5" -> {
                println("=================")
                print("Masukan text: ")
                val x = readLine()
                println("Result: ${x?.Q4()}")
                print("Apakah anda ingin kembali [y/n]: ")
                input = readLine()
                println("=================")
            }

            else -> {
                println("=================")
                println("Pilihan tidak valid!")
                print("Apakah anda ingin kembali [y/n]: ")
                input = readLine()
                println("=================")
            }
        }
    } while(input == "y" || input == "Y")
}

fun Int.Q1(): StringBuilder {
    val result = StringBuilder()
    when {
        this % 5 == 0 && this % 3 == 0 -> {
            result.append("Hello World")
        }

        this % 3 == 0 -> {
            result.append("Hello")
        }

        this % 5 == 0 -> {
            result.append("World")
        }
    }
    return result
}

fun String.Q2(): Int {
    val input1 = this.find { it == '@' }
    val isInValid: Int

    if (input1 != null) {
        val txtAt = this.map { it }.toTypedArray().indexOf('@')
        val txtBeforeAt = this.take(txtAt)

        if (txtBeforeAt.length < 20) {
            val txtAfterAt = this.slice(txtAt.rangeTo(txtAt+1)).drop(1)

            if (txtAfterAt == ".") {
                val domain = this.slice(txtAt until this.length).split(".").toTypedArray()

                if (domain.size == 3) {
                    if (domain[2] != "id") isInValid = 404
                    else isInValid = 200
                } else {
                    if (domain[2] != "co" && domain[3] != "id") {
                        isInValid = 404
                    } else isInValid = 200
                }
            } else {
                isInValid = 403
            }
        } else {
            isInValid = 402
        }

    } else {
        isInValid = 401
    }

    return isInValid
}

fun String.Q3(): String {
    val dummyPM = arrayListOf("12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23")
    val dummyAM = arrayListOf("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11")
    val dayOrNight = this.takeLast(2)
    val hour = this.take(2)

    if (hour.first().equals("0")) {
        val lastHour = hour.drop(1).toInt()

        if (dayOrNight == "PM") {
            return "${dummyPM[lastHour]}:${this.slice(3..4)}"
        } else {
            return "${dummyAM[lastHour]}:${this.slice(3..4)}"
        }

    } else {
        val lastHour = hour.drop(1).toInt()

        if (dayOrNight == "PM") {
            if (hour == "12") {
                return "${dummyPM[0]}:${this.slice(3..4)}"
            } else {
                return "${dummyPM[lastHour]}:${this.slice(3..4)}"
            }
        } else {
            if (hour == "12") {
                return "${dummyAM[0]}:${this.slice(3..4)}"
            } else {
                return "${dummyAM[lastHour]}:${this.slice(3..4)}"
            }
        }
    }
}

fun String.Q4(): StringBuilder {
    var result = StringBuilder().append("")
    for (i in this.length - 1 downTo 0) {
        result = result.append(this[i])
    }
    return result
}

fun String.Q5(): Boolean {
    var reverseText = StringBuilder().append("")
    for (i in this.length - 1 downTo 0) {
        reverseText = reverseText.append(this[i])
    }
    return this.equals(reverseText.toString(), true)
}