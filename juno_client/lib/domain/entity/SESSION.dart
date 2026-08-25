import 'package:juno_client/domain/models/Usuario.dart';
import 'package:juno_client/domain/types/UserType.dart';

// ignore: 
class SESSION {
  static final SESSION instance = SESSION._internal();
 

  int? _id;
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


  SESSION composser(final Usuario usuario){
    _id = usuario.id!;
    nombre = usuario.nombre;
    apellidos = usuario.apellidos;
    dni = usuario.dni;
    email = usuario.email;
    telefono = usuario.telefono;
    nacimiento = usuario.nacimiento;
    rol = usuario.rol;
    estado = usuario.estado;
    idCentro = usuario.idCentro;
    idAula = usuario.idAula;
    return SESSION._internal();
  }



  void usuarioSaveSession(final Usuario usuario){
    _id = usuario.id ?? null;
    nombre = usuario.nombre ?? null;
    apellidos = usuario.apellidos ?? null;
    dni = usuario.dni ?? null;
    email = usuario.email ?? null;
    telefono = usuario.telefono ?? null;
    nacimiento = usuario.nacimiento ?? null;
    rol = usuario.rol ?? null;
    estado = usuario.estado ?? null;
    idCentro = usuario.idCentro ?? null;
    idAula = usuario.idAula ?? null;
  }

  SESSION._internal();
}