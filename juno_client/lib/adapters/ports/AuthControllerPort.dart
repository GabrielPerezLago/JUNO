import 'package:juno_client/config/security/storage.security.dart';
import 'package:juno_client/domain/entity/SESSION.dart';
import 'package:juno_client/domain/models/Token.dart';
import 'package:juno_client/domain/models/Usuario.dart';
import 'package:juno_client/infraestructure/services/AuthService.dart';
import 'package:jwt_decoder/jwt_decoder.dart';

class AuthControllerPort {
  final JunoTokenStorageRepository tokenStorage;
  final AuthService authService;
  final SESSION session;

  AuthControllerPort({
    required this.tokenStorage,
    required this.authService,
    required this.session,
  });


  Future<bool> login(String email, String password) async {
    Token token = await authService.login(email, password);

    // Decodeamos el token

    Map<String, dynamic> tokenDecoded =  JwtDecoder.decode(token.getToken);


    tokenStorage.reWriteToken(token.getToken);
    tokenStorage.reWriteRefreshToken(token.getRefreshToken);

    _decodeTokenAndGetUsuario(token.getToken, saveInSession: true);

    return await this.session.email != null;

    
  }



  /**
   * @params Token
   * @params SaveSession
   * 
   * Metodod para decodificar el token y devuelve el usuario con los datos del token . y un guardado de session automatico
   * 
   */
  Usuario _decodeTokenAndGetUsuario(final String token , {bool saveInSession = true}) {
    Map<String, dynamic> tokenDecoded = JwtDecoder.decode(token);

    final usuario = Usuario(
       id: tokenDecoded['id'],
       nombre: tokenDecoded['nombre'],
       apellidos: tokenDecoded['apellidos'],
       dni: tokenDecoded['dni'],
       email: tokenDecoded['subject'],
       telefono: tokenDecoded['telefono'],
       nacimiento: tokenDecoded['nacimiento'],
       estado: tokenDecoded['estado'] == '' ? null: tokenDecoded['estado'],
       rol: tokenDecoded['rol'] == '' ? 'USUARIO': tokenDecoded['rol'],
       idCentro: tokenDecoded['id_centro'] == '' ? null : tokenDecoded['id_centro'],
       idAula: tokenDecoded['id_aula'] == '' ? null : tokenDecoded['id_aula']
    );

    if (saveInSession) {
      this.session.usuarioSaveSession(usuario);
    }

    return usuario;
  }


}