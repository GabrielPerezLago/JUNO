import 'package:flutter/material.dart';

class LoginController  {
  const LoginController();

  static dynamic validateLoginArgs(Map<String, String> args) {
    if (!args.containsKey('email') || !args.containsKey('password')) return 'Parametros no validos';

    for ( var entry in args.entries) {
      if (entry.key == 'email' || entry.key == 'password') {
        if (entry.value.isEmpty) return 'Rellena los campos vacíos';
      }
    }
  }


  static void loginAndRegister(final Type type, final Map<String, String> params ) {

  }
}

class Type {
  String? type;

  Type({
    this.type = null
  });

  static final Type LOGIN = Type(type: 'login') ;
  static final REGISTER = Type(type: 'registrer');

  String? getType() => this.type!;
}