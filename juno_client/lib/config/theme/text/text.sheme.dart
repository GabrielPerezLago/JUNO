import 'package:flutter/material.dart';


abstract class JunoTextThemeProvider {

  static TextTheme dark() { 
    return ThemeData.dark().textTheme.apply(
      bodyColor: Colors.white,
      fontFamily: 'Merienda'
    );

  }

  static TextTheme light() => ThemeData.light().textTheme.apply(
    bodyColor: Color(0xff333333),
    fontFamily: 'Merienda'
  );

   

}

