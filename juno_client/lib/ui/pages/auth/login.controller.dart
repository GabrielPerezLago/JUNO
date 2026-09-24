import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:juno_client/domain/interfaces/types.implement.dart';
import 'package:juno_client/infraestructure/adapters/AuthAdapter.dart';

class LoginViewController  {

  static AuthAdapter? _adapter;


  static dynamic validateLoginArgs(Map<String, String> args) {

    final String emptyParamMsg = 'Rellena los campos vacíos';

    if (!args.containsKey('email') || !args.containsKey('password')) return emptyParamMsg;

    for ( var entry in args.entries) {


      if (entry.key == 'email' || entry.key == 'password') {
        if (entry.value.isEmpty) return emptyParamMsg;
      }

      if(entry.key == 'repeatPassword') {
        if (entry.value.isEmpty) return emptyParamMsg;
      }

      if(entry.key == 'dni') {
        if (entry.value.isEmpty) return emptyParamMsg;
      }
    }
  }


  static dynamic loginAndRegister(final BuildContext? context ,final Type type, final Map<String, String> params ) async {
    try {
      _adapter = AuthAdapter();
      //  tipo de la oparacion 
      if (type.valueOf(Type.LOGIN)) {
        final String? response = await _adapter!.login(
          email: _getParam(params, 'email')!,
          password: _getParam(params, 'password')!
        );

        // Comprobacion e migracion
        if (response == null) {
          context?.go('/home');
        } else {
          return response;
        }
      } else if (type.valueOf(Type.REGISTER)) {

          late String? message;

          if (params['password'] != params['repeatPassword']) {
            message = 'Las contraseñas no son iguales';
            return message;
          }

          List<String> nombreCompleto =  params['nombre']
          .toString()
          .split(' ')
          .toList();

          final paramsMap = {
            'nombre': nombreCompleto[0].trim(),
            if (nombreCompleto.length > 1) 'apellidos': nombreCompleto[1].trim() + ' ' + nombreCompleto[2].trim(),
            'email': params['email'],
            'password': params['password'],
            'dni': params['dni']
          };

          print(paramsMap);


          message = await _adapter!.register(paramsMap);

          if (message == null) { 
            context?.go('/home'); 
          } else {
            return message;
          }

      } else {
        throw Exception('Tipo no valido');
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

class Type implements TypeManual {

  List<String> _types = [
    'LOGIN',
    'REGISTER'
  ];
  String? _type;

  Type(String type) {
    if (!_types.contains(type)) {
      throw Exception(' Class TYPE  : Tipo no permitudo');
    }

    _type = type;
  }

  static final LOGIN = Type('LOGIN') ;
  static final REGISTER = Type('REGISTER');

  @override
  type() => _type;

  @override
  bool valueOf(TypeManual type) => type.type() == _type;
}