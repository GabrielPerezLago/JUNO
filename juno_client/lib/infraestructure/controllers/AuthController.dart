import 'package:juno_client/config/security/storage.security.dart';
import 'package:juno_client/domain/seession/SESSION.dart';
import 'package:juno_client/domain/models/JnError.dart';
import 'package:juno_client/domain/models/Token.dart';
import 'package:juno_client/domain/models/Usuario.dart';
import 'package:juno_client/domain/types/UserType.dart';
import 'package:juno_client/infraestructure/managers/token.manager.dart';
import 'package:juno_client/infraestructure/services/AuthService.dart';
import 'package:juno_client/uitls/DateTimeUtils.dart';
import 'package:jwt_decoder/jwt_decoder.dart';

class AuthController {
  final JnTokenStorageRepository tokenStorage;
  final AuthService authService;
  final SESSION session;

  AuthController({
    required this.tokenStorage,
    required this.authService,
    required this.session,
  });


  Future<bool> loginByToken(String refreshToken) async  {
    try {
      final Token? token = await authService.loginByToken(refreshToken);

      if (token == null) return false;

      // gaurdamos tokenms
      tokenStorage.reWriteRefreshToken(token.getRefreshToken);
      tokenStorage.reWriteToken(token.getToken);
      
      _decodeTokenAndGetUsuario(token.getToken);

      return true;


    } catch (ex) {
      print(ex);
      throw Exception(ex);
    }
  }

  Future<JnError?> login(String email, String password) async {
    try {
      TokenRespManager tokenResponse = await authService.login(email, password);

      final tokenOrError = tokenResponse.valueOf(TokenRespType.TOKEN)
          ? tokenResponse.TOKEN
          : tokenResponse.ERROR;

      if (tokenOrError is JnError) return tokenOrError;

      tokenOrError as Token;

      // Decodeamos el token
      Map<String, dynamic> tokenDecoded = JwtDecoder.decode(
        tokenOrError.getToken,
      );

      tokenStorage.reWriteToken(tokenOrError.getToken);
      tokenStorage.reWriteRefreshToken(tokenOrError.getRefreshToken);

      _decodeTokenAndGetUsuario(tokenOrError.getToken, saveInSession: true);

      return null;
    } catch (ex) {
      print(ex);
      throw Exception(ex);
    }
  }

  Future<JnError?> register(Map<String, dynamic> params) async {
    final tokenOrError = await authService.registerService(params)
    .then((TokenRespManager tokenManager) {
      if (tokenManager.valueOf(TokenRespType.TOKEN)) {
        return tokenManager.TOKEN;
      } else {
        return tokenManager.ERROR;
      }
    });

    if (tokenOrError is JnError) return tokenOrError;

    tokenOrError as Token;

    tokenStorage.reWriteRefreshToken(tokenOrError.getRefreshToken);
    tokenStorage.reWriteToken(tokenOrError.getToken);

    _decodeTokenAndGetUsuario(tokenOrError.getToken, saveInSession: true);

    return null;
  }

  /// @params Token
  /// @params SaveSession
  ///
  /// Metodod para decodificar el token y devuelve el usuario con los datos del token . y un guardado de session automatico
  ///
  Usuario _decodeTokenAndGetUsuario(
    final String token, {
    bool saveInSession = true,
  }) {
    Map<String, dynamic> tokenDecoded = JwtDecoder.decode(token);

    print("""
      TOKEN DATE $tokenDecoded
    """);

    final usuario = Usuario(
      id: int.tryParse(tokenDecoded['jti']),
      nombre: tokenDecoded['nombre'],
      apellidos: tokenDecoded['apellidos'],
      dni: tokenDecoded['dni'],
      email: tokenDecoded['sub'],
      telefono: tokenDecoded['telefono'],
      nacimiento: DateTimeUtils.parseStringToDate(tokenDecoded['nacimiento']),
      estado: tokenDecoded['estado'] == '' ? null : tokenDecoded['estado'],
      rol: tokenDecoded['rol'] == ''
          ? UserType.USUARIO
          : UserType.getUserTypeByParam(tokenDecoded['rol']),
      idCentro: tokenDecoded['id_centro'] == ''
          ? null
          : tokenDecoded['id_centro'] as int,
      idAula: tokenDecoded['id_aula'] == ''
          ? null
          : int.tryParse(tokenDecoded['id_aula']),
    );

    if (saveInSession) {
      session.usuarioSaveSession(usuario);
    }
    var email = session.email;
    print("""
          (( | Email : $email | ))
          """);
    return usuario;
  }
}
