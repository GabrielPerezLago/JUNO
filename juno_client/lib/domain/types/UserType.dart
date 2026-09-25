
import 'package:juno_client/domain/interfaces/types.implement.dart';

class UserType implements TypeManual{
  late final String _type;

  static final List<String> _types = [
    'USUARIO',
    'DIRECCION',
    'DOCENTE',
    'ADMINISTRACION'
  ];

  UserType(final String type) {

    if (!_types.contains(type)) {
      throw Exception('Tipo de usuario no valido');
    }
    _type = type;
  }


  static final USUARIO = UserType('USUARIO');
  
  
  static UserType getUserTypeByParam(String type) {
    return UserType(type.toUpperCase());
  }

  @override
  type() {
    return _type;
  }

  @override
  bool valueOf(TypeManual type) {
    return _type == type.type();
  }
}