import 'package:juno_client/config/security/storage.security.dart';
import 'package:juno_client/domain/entity/SESSION.dart';
import 'package:juno_client/domain/models/Token.dart';
import 'package:juno_client/infraestructure/services/AuthService.dart';

class AuthControllerPort {
  final JunoTokenStorageRepository tokenStorage;
  final AuthService authService;
  final SESSION session;

  AuthControllerPort({
    required this.tokenStorage,
    required this.authService,
    required this.session
  });


  Future<bool> login(String email, String password) async {
    Token token = await authService.login(email, password);

    tokenStorage.reWriteToken(token.getToken);
    tokenStorage.reWriteRefreshToken(token.getRefreshToken);


    return await true;

    
  }
}