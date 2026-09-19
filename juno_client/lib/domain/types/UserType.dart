
class UserType {
  final String type;

  static final List<String> _types = [
    'USUARIO',
    'DIRECCION',
    'DOCENTE',
    'ADMINISTRACION'
  ];

  UserType({
    required this.type
  }) {

    if (!_types.contains(type)) {
      throw Exception('Tipo de usuario no valido');
    }
  }


  static final USUARIO = UserType(type: 'USUARIO');
  
  
  static UserType getUserTypeByParam(String type) {
    return UserType(type: type.toUpperCase());
  }
}