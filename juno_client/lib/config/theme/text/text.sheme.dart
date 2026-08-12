import 'package:flutter/material.dart';


abstract class JunoTextThemeProvider {

  static TextTheme dark() => ThemeData.dark().textTheme.apply(
    bodyColor: Colors.white
  );

  static TextTheme light() => ThemeData.light().textTheme.apply(
    bodyColor: Colors.black
  );

}