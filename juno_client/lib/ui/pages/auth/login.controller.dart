import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:juno_client/domain/models/JnError.dart';
import 'package:juno_client/infraestructure/adapters/AuthAdapter.dart';

class LoginViewController  {

  static AuthAdapter? _adapter;


  static dynamic validateLoginArgs(Map<String, String> args) {
    if (!args.containsKey('email') || !args.containsKey('password')) return 'Parametros no validos';

    for ( var entry in args.entries) {
      if (entry.key == 'email' || entry.key == 'password') {
        if (entry.value.isEmpty) return 'Rellena los campos vacíos';
      }
    }
  }


  static dynamic loginAndRegister(final BuildContext? context ,final Type type, final Map<String, String> params ) async {
    try {
      _adapter = AuthAdapter();
      //  tipo de la oparacion 
      if (type.getType() == 'LOGIN') {
        //respuesta del adaptador

        
        final JnError? response = await _adapter!.login(
          email: _getParam(params, 'email')!,
          password: _getParam(params, 'password')!
        );

        // Comprobacion e migracion
        if (response == null) {
          context!.go('/home');
        } else {
          return response;
        }
      }
    } catch (ex) {
      context!.go('/con-error');
    }
  }






  static String? _getParam(final Map<String, String> params, final dynamic param) {
    for (final entry in params.entries) {
      if (entry.key == param) {
        return entry.value;
      }
    }
    return null;
  }
}

class Type {
  String? type;

  Type({
    this.type
  }) {
    if (type != 'LOGIN' && type != 'REGISTER') {
      throw Exception(' Class TYPE  : Tipo no permitudo');
    }
  }

  static final LOGIN = Type(type: 'LOGIN') ;
  static final REGISTER = Type(type: 'REGISTER');

  String? getType() => type!;
}