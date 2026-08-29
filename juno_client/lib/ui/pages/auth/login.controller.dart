import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:juno_client/adapters/AuthControllerAdapter.dart';

class LoginController  {

  static AuthControllerAdapter? _adapter;


  static dynamic validateLoginArgs(Map<String, String> args) {
    if (!args.containsKey('email') || !args.containsKey('password')) return 'Parametros no validos';

    for ( var entry in args.entries) {
      if (entry.key == 'email' || entry.key == 'password') {
        if (entry.value.isEmpty) return 'Rellena los campos vacíos';
      }
    }
  }


  static dynamic loginAndRegister(final BuildContext context ,final Type type, final Map<String, String> params ) async {
    _adapter = AuthControllerAdapter();
    //  tipo de la oparacion 
    if (type.getType() == 'LOGIN') {
      //respuesta del adaptador

      
      final String? response = await _adapter!.login(
        email: _getParam(params, 'email')!,
        password: _getParam(params, 'password')!
      );

      // Comprobacion e migracion
      if (response == null) {
        context.go('/home');
      } else {
        return 'No se ha podido Iniciar Session ';
      }
    }
  }






  static String? _getParam(final Map<String, String> params, final dynamic param) {
    for (final entry in params.entries) {
      if (entry.key == param) {
        return entry.value;
      }
    }
  }
}

class Type {
  String? type;

  Type({
    this.type = null
  }) {
    if (this.type != 'LOGIN' && this.type != 'REGISTER') {
      throw Exception(' Class TYPE  : Tipo no permitudo');
    }
  }

  static final LOGIN = Type(type: 'LOGIN') ;
  static final REGISTER = Type(type: 'REGISTER');

  String? getType() => this.type!;
}