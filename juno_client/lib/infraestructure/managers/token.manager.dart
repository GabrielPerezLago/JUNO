import 'package:juno_client/domain/interfaces/types.implement.dart';
import 'package:juno_client/domain/models/JnError.dart';
import 'package:juno_client/domain/models/Token.dart';

class TokenRespManager {
  Map<String, dynamic> args;
  TokenRespType type;

  TokenRespManager({required this.args, required this.type});

  get TOKEN {
    if (type.valueOf(TokenRespType.TOKEN) && args.containsKey("token")) {
      List<String> tokenData = [];
      args.entries.forEach((entry) => tokenData.add(entry.value));

      if (!tokenData.isEmpty) return Token(tokenData[0], tokenData[1]);
    }
    return null;
  }

  get ERROR {
    if (type.valueOf(TokenRespType.ERROR) && args.containsKey('statusCode')) {
      return JnError.fromErrorResponse(args);
    }
    return null;
  }

  bool valueOf(TokenRespType tp) {
    return type.valueOf(tp);
  }
}

class TokenRespType implements TypeManual {
  final _tipes = ["ERROR", "TOKEN"];
  String? _type;

  TokenRespType(final String type) {
    if (!_tipes.contains(type)) {
      throw Exception("El tipo enviado a TokenRespType no es valido");
    }
    _type = type;
  }

  static final ERROR = TokenRespType("ERROR");
  static final TOKEN = TokenRespType("TOKEN");

  @override
  dynamic type() => _type;

  @override
  bool valueOf(TypeManual type) => type.type() == _type;
}
