import 'dart:convert';

import 'package:http/http.dart';


abstract interface class HttpReqRepository {

  static final String _API_URI = "";
  static final appJsonHeader = {
        'Content-Type': 'application/json'
  };


  static Future<Response> GET(final String endpoint) async {
    return await get(
      Uri.parse('$_API_URI$endpoint'),
      headers: appJsonHeader
    )
    .timeout(Duration(seconds: 10));
  }

 
  static Future<Response> POST_REQUEST(final String endpoint, final Map<String, dynamic> args) async => await post(
      Uri.parse('$_API_URI$endpoint'),
      headers: appJsonHeader,
      body: jsonEncode(args)
    );
  } 

  static Future<Response> POST_QUERY(final String endpoint, final Map<String, dynamic> args) async {

      String composedEndpoint = '$endpoint?';
      
      args.forEach((key, value) {
        if (composedEndpoint.endsWith('?')) {
          composedEndpoint += '$key=$value';
        } else {
          composedEndpoint += '&$key=$value';
        }
      });


      return await post(
        Uri.parse('$_API_URI$composedEndpoint'),
        headers: appJsonHeader
      );
  }


  static Future<Response> PORT_PATH(final String endpoint) async {
    return await post(
      Uri.parse('$_API_URI$endpoint'),
      headers: appJsonHeader
    );
  }





}