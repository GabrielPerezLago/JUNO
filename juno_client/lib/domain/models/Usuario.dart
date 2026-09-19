import 'package:juno_client/domain/types/UserType.dart';

class Usuario {
  int? id;
  String? nombre;
  String? apellidos;
  String? dni;
  String? email;
  String? telefono;
  DateTime? nacimiento;
  UserType? rol;
  String? estado;
  int? idCentro;
  int? idAula;

  Usuario({
    this.id,
    this.nombre,
    this.apellidos,
    this.dni,
    this.email,
    this.telefono,
    this.nacimiento,
    this.estado,
    this.rol,
    this.idAula,
    this.idCentro
  });

  Map<String, dynamic> toMap() {
    return {
      "id" : id!,
      "nombre": nombre!,
      "apellidos": apellidos!,
      "dni": dni!,
      "email": email!,
      "telefono": telefono!,
      "nacimiento": nacimiento!,
      "rol": rol!.type,
      "estado": estado!,
      "idCentro": idCentro!,
      "idAula": idAula!
    };
  }

}