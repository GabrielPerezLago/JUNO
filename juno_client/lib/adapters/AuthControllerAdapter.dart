import 'package:juno_client/adapters/ports/AuthControllerPort.dart';
import 'package:juno_client/config/security/storage.security.dart';
import 'package:juno_client/domain/entity/SESSION.dart';
import 'package:juno_client/infraestructure/services/AuthService.dart';

class AuthControllerAdapter  {
  AuthControllerPort? _port;

  final AuthService service = AuthService();
  final JunoTokenStorageRepository tokenStorage = JunoTokenStorageRepository();
  final SESSION session = SESSION.instance;


  AuthControllerAdapter() {
    _port = AuthControllerPort(
      tokenStorage: this.tokenStorage, 
      authService: this.service, 
      session: this.session
    );
  }



}