import 'package:flutter/material.dart';
import 'package:juno_client/config/theme/colors/scaffold.color.dart';
import 'package:juno_client/config/theme/schemes/color.scheme.dart';
import 'package:juno_client/config/theme/schemes/elevatedButton.theme.dart';
import 'package:juno_client/config/theme/schemes/input.decoration.dart';
import 'package:juno_client/config/theme/text/text.sheme.dart';



class JunoThemeProvider {

  const JunoThemeProvider();


  static final LIGHT = ThemeData(
    useMaterial3: true,
    colorScheme: JunoColorScheme.light(),
    textTheme: JunoTextThemeProvider.light(),
    scaffoldBackgroundColor: ScaffoldColor.light, 
    elevatedButtonTheme: junoElevatedButtonTheme,
    inputDecorationTheme: junoInputDecorationTheme
  );

  static final DARK = ThemeData(
    useMaterial3: true,
    brightness: Brightness.dark,
    colorScheme: JunoColorScheme.dark(),
    textTheme: JunoTextThemeProvider.dark(),
    scaffoldBackgroundColor: ScaffoldColor.dark,
    elevatedButtonTheme: junoElevatedButtonTheme,
    inputDecorationTheme: junoInputDecorationTheme
  );

}