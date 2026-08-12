import 'package:flutter/material.dart';
import 'package:juno_client/config/app/app.tools.dart';

// ignore: must_be_immutable
class LoginDesktopLayout extends StatefulWidget {

    @override
  State<StatefulWidget> createState() => _LoginDesktopState();
}

class _LoginDesktopState extends State<LoginDesktopLayout> {


  @override
  Widget build(BuildContext context) => Scaffold(
    body: SafeArea(child: SingleChildScrollView(
      child: Align(
        alignment: Alignment.center,
        child: Container(
          width: width(context) * 0.8,
          height: height(context) * 0.6,
          decoration: BoxDecoration(
            color: junoColorScheme(context).primary,
          ),
          alignment: Alignment.center,
          child: Text('Hola Como vamos'),
        )
      )
    )),
  );
}