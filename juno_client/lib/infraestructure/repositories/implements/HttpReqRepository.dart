import 'dart:convert';

import 'package:flutter/foundation.dart';
import 'package:http/http.dart';


abstract interface class HttpReqRepository {
  static final Duration TIME_OUT = Duration(seconds: 7);

  static final String _API_URI = "http://100.120.82.46:8080/";
  static final appJsonHeader = {
        'Content-Type': 'application/json'
  };


  static Future<Response> GET(final String endpoint) async {
    try {
       return await get(
        Uri.parse('$_API_URI$endpoint'),
        headers: appJsonHeader
      ).timeout(TIME_OUT);
    } catch (except) {
      print(except);
      rethrow;
    }
  }

 
  static Future<Response> POST_REQUEST(final String endpoint, final Map<String, dynamic> args) async  {

    try {
      return await post(
        Uri.parse('$_API_URI$endpoint'),
        headers: appJsonHeader,
        body: jsonEncode(args)
      ).timeout(TIME_OUT);
    } catch(ex) {
      print(ex);
      rethrow;
    }

  }
   

  static Future<Response> POST_QUERY(final String endpoint, final Map<String, dynamic> args) async {
      try {
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
        ).timeout(TIME_OUT);
      } catch (except) {
        print(except);
        rethrow;
      }
  }


  static Future<Response> PORT_PATH(final String endpoint) async {
    try {
      
      return await post(
        Uri.parse('$_API_URI$endpoint'),
        headers: appJsonHeader
      ).timeout(TIME_OUT);


    } catch (except) {
      print(except);
      rethrow;
    }
  }





}