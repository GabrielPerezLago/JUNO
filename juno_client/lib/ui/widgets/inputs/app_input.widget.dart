import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:juno_client/config/app/app.tools.dart';

class JnInput extends StatefulWidget {
  final TextEditingController controller;
  final bool isOcutable;
  final String lblTextTittle;

  JnInput({
    required this.controller,
    this.isOcutable = false,
    required this.lblTextTittle,
  });
  
  @override
  State<StatefulWidget> createState() => _JnInputState();

}


class _JnInputState extends State<JnInput> {
  TextEditingController get _controller => widget.controller;
  bool get _ocult => widget.isOcutable;
  String get _lblText => widget.lblTextTittle;

  bool _hidden = true;
  
  _JnInputState();

  @override
  Widget build(BuildContext context) => TextField(
    controller: _controller,
    decoration: InputDecoration(
      labelText: _lblText,
      enabledBorder: _jnIputBorder(),
      focusedBorder: _jnIputBorder()
    ),
  );




  OutlineInputBorder _jnIputBorder() => OutlineInputBorder(
    borderRadius: BorderRadius.circular(50),
    borderSide: BorderSide(
      width: width(context) * 0.001
    ),
    gapPadding: 20
  );

}