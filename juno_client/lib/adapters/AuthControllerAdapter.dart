import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:juno_client/adapters/ports/AuthControllerPort.dart';
import 'package:juno_client/config/security/storage.security.dart';
import 'package:juno_client/domain/entity/SESSION.dart';
import 'package:juno_client/infraestructure/services/AuthService.dart';

class AuthControllerAdapter  {
  AuthControllerPort? _port;
  BuildContext context;
  final AuthService _service = AuthService();
  final JunoTokenStorageRepository _tokenStorage = JunoTokenStorageRepository();
  final SESSION _session = SESSION.instance;


  AuthControllerAdapter({
    required this.context
  }) {
    _port = AuthControllerPort(
      tokenStorage: this._tokenStorage, 
      authService: this._service, 
      session: this._session
    );
  }


  Future<String> login({
    String email = '',
    String password= ''
  }) async  {
    String? errMsg = '';
    bool? isFinish = await _port?.login(email, password);
    if(isFinish!) {
      errMsg = _session.nombre;
      // context.go('home');
      // return;
    } else {
      errMsg = 'Algun parametro no es valido';
    }
  

    return errMsg!;
  }



}