import 'dart:convert';

import 'package:http/http.dart';

abstract class HttpRespository {
  static final String _URI = "https://juno.gabriel.living";
  static final Duration timeout = Duration(milliseconds: 7000);

  Future<Response> GET(final String endpoint) async {
    try {
      return await get(
        Uri.parse('$_URI$endpoint'),
        headers: {'Content-Type': 'application/json'},
      ).timeout(timeout);
    } catch (ex) {
      print(ex);
      rethrow;
    }
  }

  POST_ENTITY POST(final String endpoint) {
    return POST_ENTITY(uri: '$_URI$endpoint');
  }
}

class POST_ENTITY {
  String uri;

  POST_ENTITY({required this.uri});

  Future<Response> QUERY(final Map<String, dynamic> args) async {
    try {
      String composedEndpoint = '$uri?';

      args.forEach((key, value) {
        if (composedEndpoint.endsWith('?')) {
          composedEndpoint += '$key=$value';
        } else {
          composedEndpoint += '&$key=$value';
        }
      });

      return await post(
        Uri.parse(composedEndpoint),
        headers: {'Content-Type': 'application/json'},
      );
    } catch (ex) {
      print(ex);
      rethrow;
    }
  }

  Future<Response> PATH(final String pathParam) async {
    try {
      return await post(
        Uri.parse('$uri$pathParam'),
        headers: {'Content-Type': 'application/json'},
      ).timeout(HttpRespository.timeout);
    } catch (ex) {
      print(ex);
      rethrow;
    }
  }

  Future<Response> BODY(final Map<String, dynamic> args) async {
    try {
      return await post(
        Uri.parse(uri),
        headers: {'Content-Type': 'application/json'},
        body: jsonEncode(args),
      ).timeout(HttpRespository.timeout);
    } catch (ex) {
      print(ex);
      rethrow;
    }
  }

  Future<Response> HADERS(final Map<String, String> headersArgs) async {
    try {
      return await post(
        Uri.parse('$uri'),
        headers: headersArgs,
      ).timeout(HttpRespository.timeout);
    } catch (ex) {
      print(ex);
      throw Exception(ex);
    }
  }
}
