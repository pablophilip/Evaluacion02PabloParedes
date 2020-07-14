-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-07-2020 a las 19:12:30
-- Versión del servidor: 10.3.16-MariaDB
-- Versión de PHP: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `anfp`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

CREATE TABLE `ciudad` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ciudad`
--

INSERT INTO `ciudad` (`codigo`, `nombre`) VALUES
(13, 'Talcahuano');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `divicion`
--

CREATE TABLE `divicion` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `divicion`
--

INSERT INTO `divicion` (`codigo`, `nombre`) VALUES
(1, 'primera divicion'),
(2, 'amateur nuevo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo`
--

CREATE TABLE `equipo` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `jugador` int(11) NOT NULL,
  `id_cuidad` int(11) NOT NULL,
  `id_divicion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `equipo`
--

INSERT INTO `equipo` (`codigo`, `nombre`, `jugador`, `id_cuidad`, `id_divicion`) VALUES
(1, 'Huachipato', 30, 13, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadio`
--

CREATE TABLE `estadio` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `id_cuidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estadio`
--

INSERT INTO `estadio` (`codigo`, `nombre`, `capacidad`, `id_cuidad`) VALUES
(1980, 'Estadio Cap', 2000, 13),
(2512, 'El Morro', 500, 13);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jugador`
--

CREATE TABLE `jugador` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `fechanacimiento` date NOT NULL,
  `sueldo` int(11) NOT NULL,
  `id_equipo` int(11) NOT NULL,
  `id_posicion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `posicion`
--

CREATE TABLE `posicion` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `posicion`
--

INSERT INTO `posicion` (`codigo`, `nombre`) VALUES
(1, 'arquero'),
(2, 'defensa'),
(3, 'medio campista'),
(4, 'volante derecho');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` varchar(15) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellido`, `password`) VALUES
('1', 'Pablo', 'Paredes', '1234');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `v_equipos`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `v_equipos` (
`codigo` int(11)
,`nombre` varchar(50)
,`jugador` int(11)
,`c_codigo` int(11)
,`c_nombre` varchar(50)
,`d_codigo` int(11)
,`d_nombre` varchar(50)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `v_estadios`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `v_estadios` (
`codigo` int(11)
,`nombre` varchar(50)
,`capacidad` int(11)
,`c_codigo` int(11)
,`c_nombre` varchar(50)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `v_jugador`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `v_jugador` (
`id` int(11)
,`nombre` varchar(50)
,`apellido` varchar(50)
,`fechanacimiento` date
,`sueldo` int(11)
,`e_codigo` int(11)
,`e_nombre` varchar(50)
,`p_codigo` int(11)
,`p_nombre` varchar(50)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `v_equipos`
--
DROP TABLE IF EXISTS `v_equipos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_equipos`  AS  (select `eq`.`codigo` AS `codigo`,`eq`.`nombre` AS `nombre`,`eq`.`jugador` AS `jugador`,`ciu`.`codigo` AS `c_codigo`,`ciu`.`nombre` AS `c_nombre`,`dic`.`codigo` AS `d_codigo`,`dic`.`nombre` AS `d_nombre` from ((`equipo` `eq` join `ciudad` `ciu` on(`eq`.`id_cuidad` = `ciu`.`codigo`)) join `divicion` `dic` on(`eq`.`id_divicion` = `dic`.`codigo`))) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `v_estadios`
--
DROP TABLE IF EXISTS `v_estadios`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_estadios`  AS  (select `estadio`.`codigo` AS `codigo`,`estadio`.`nombre` AS `nombre`,`estadio`.`capacidad` AS `capacidad`,`ciudad`.`codigo` AS `c_codigo`,`ciudad`.`nombre` AS `c_nombre` from (`estadio` join `ciudad` on(`estadio`.`id_cuidad` = `ciudad`.`codigo`))) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `v_jugador`
--
DROP TABLE IF EXISTS `v_jugador`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_jugador`  AS  (select `ju`.`id` AS `id`,`ju`.`nombre` AS `nombre`,`ju`.`apellido` AS `apellido`,`ju`.`fechanacimiento` AS `fechanacimiento`,`ju`.`sueldo` AS `sueldo`,`eq`.`codigo` AS `e_codigo`,`eq`.`nombre` AS `e_nombre`,`po`.`codigo` AS `p_codigo`,`po`.`nombre` AS `p_nombre` from ((`jugador` `ju` join `equipo` `eq` on(`ju`.`id_equipo` = `eq`.`codigo`)) join `posicion` `po` on(`ju`.`id_posicion` = `po`.`codigo`))) ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `divicion`
--
ALTER TABLE `divicion`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `id_cuidad` (`id_cuidad`),
  ADD KEY `id_divicion` (`id_divicion`);

--
-- Indices de la tabla `estadio`
--
ALTER TABLE `estadio`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `id_cuidad` (`id_cuidad`);

--
-- Indices de la tabla `jugador`
--
ALTER TABLE `jugador`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_equipo` (`id_equipo`),
  ADD KEY `id_posicion` (`id_posicion`);

--
-- Indices de la tabla `posicion`
--
ALTER TABLE `posicion`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD CONSTRAINT `equipo_ibfk_1` FOREIGN KEY (`id_divicion`) REFERENCES `divicion` (`codigo`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `equipo_ibfk_2` FOREIGN KEY (`id_cuidad`) REFERENCES `ciudad` (`codigo`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `estadio`
--
ALTER TABLE `estadio`
  ADD CONSTRAINT `estadio_ibfk_1` FOREIGN KEY (`id_cuidad`) REFERENCES `ciudad` (`codigo`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `jugador`
--
ALTER TABLE `jugador`
  ADD CONSTRAINT `jugador_ibfk_1` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`codigo`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `jugador_ibfk_2` FOREIGN KEY (`id_posicion`) REFERENCES `posicion` (`codigo`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
