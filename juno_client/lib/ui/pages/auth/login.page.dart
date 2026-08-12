import 'package:flutter/material.dart';

// ignore: must_be_immutable
class LoginViewPage extends StatefulWidget {

  Widget child;

  LoginViewPage({
      super.key, 
      required this.child
  });

@override
  State<StatefulWidget> createState() => _LoginViewState(scene: this.child);

}


class _LoginViewState extends State<LoginViewPage> {
  Widget scene;
  _LoginViewState({required this.scene}); 

  @override
  Widget build(BuildContext context) => this.scene;

}