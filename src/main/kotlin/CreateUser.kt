/*
 * Nextcloud Migadu Create User
 * Copyright (C) 2021 SeMudando
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package br.com.semudando

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import io.github.rybalkinsd.kohttp.dsl.httpPost
import io.github.rybalkinsd.kohttp.ext.asString
import io.github.rybalkinsd.kohttp.ext.url
import kotlin.system.exitProcess

class CreateUser : CliktCommand() {
    val username by argument()
    val displayName by argument()
    val password by argument()
    val email by argument()

    val nextcloudBasicAuth by argument()
    val nextcloudApiUrl by argument()
    val migaduBasicAuth by argument()
    val migaduUrl by argument()

    override fun run() {
        createNextcloudUser()
        createMigaduUser()
    }

    private fun createNextcloudUser() {
        echo("Creating nextcloud user $username")
        val ncResponse = httpPost {
            url(nextcloudApiUrl)

            body {
                form {
                    "userid" to username
                    "displayName" to displayName
                    "password" to password
                    "email" to email
                }
            }

            header {
                "OCS-APIRequest" to true
                "Authorization" to "Basic $nextcloudBasicAuth"

            }
        }

        if (ncResponse.isSuccessful) {
            echo("Created")
        } else {
            echo("Failed " + ncResponse.asString(), err = true)
            exitProcess(-1)
        }
    }

    private fun createMigaduUser() {
        val (localpart, domain) = email.split("@")
        echo("Creating migadu user $localpart")

        val response = httpPost {
            url(migaduUrl)

            body {
                json {
                    "local_part" to localpart
                    "domain" to domain
                    "name" to displayName
                    "password" to password
                }
            }

            header {
                "Authorization" to "Basic $migaduBasicAuth"
            }
        }

        if (response.isSuccessful) {
            echo("Created")
        } else {
            echo("Failed ${response.asString()}", err = true)
            exitProcess(-1)
        }
    }
}

fun main(args: Array<String>) {
    CreateUser().main(args)
}
