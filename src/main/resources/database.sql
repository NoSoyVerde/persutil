-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Servidor: database:3306
-- Tiempo de generación: 20-11-2025 a las 11:10:49
-- Versión del servidor: 8.4.6
-- Versión de PHP: 8.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Base de datos: `persutildb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `blog`
--

CREATE TABLE `blog` (
  `id` bigint NOT NULL,
  `titulo` varchar(1024) COLLATE utf32_unicode_ci NOT NULL,
  `contenido` varchar(255) COLLATE utf32_unicode_ci NOT NULL,
  `etiquetas` varchar(1024) COLLATE utf32_unicode_ci NOT NULL,
  `fecha_creacion` datetime NOT NULL,
  `fecha_modificacion` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

--
-- Volcado de datos para la tabla `blog`
--

INSERT INTO `blog` (`id`, `titulo`, `contenido`, `etiquetas`, `fecha_creacion`, `fecha_modificacion`) VALUES
(3, 'Primer Post', 'Este es el contenido de mi primer post en el blog.', 'etiqueta1, etiqueta2', '2025-11-11 10:40:41', NULL),
(4, 'Primer Post', 'Este es el contenido de mi primer post en el blog.', 'etiqueta1, etiqueta2', '2025-11-11 10:40:41', NULL),
(5, 'Primer Post', 'Este es el contenido de mi primer post en el blog.', 'etiqueta1, etiqueta2', '2025-11-11 10:40:42', NULL),
(6, 'Primer Post', 'Este es el contenido de mi primer post en el blog.', 'etiqueta1, etiqueta2', '2025-11-11 11:27:31', NULL),
(7, 'Primer Post', 'Este es el contenido de mi primer post en el blog.', 'etiqueta1, etiqueta2', '2025-11-11 11:27:33', NULL),
(8, 'Primer Post', 'Este es el contenido de mi primer post en el blog.', 'etiqueta1, etiqueta2', '2025-11-11 11:27:34', NULL),
(9, 'Primer Post', 'Este es el contenido de mi primer post en el blog.', 'etiqueta1, etiqueta2', '2025-11-11 11:27:35', NULL),
(10, 'Primer Post', 'Este es el contenido de mi primer post en el blog.', 'etiqueta1, etiqueta2', '2025-11-11 11:27:35', NULL),
(11, 'Primer Post', 'Este es el contenido de mi primer post en el blog.', 'etiqueta1, etiqueta2', '2025-11-11 11:27:36', NULL),
(12, 'Primer Post', 'Este es el contenido de mi primer post en el blog.', 'etiqueta1, etiqueta2', '2025-11-11 11:27:36', NULL),
(13, 'Primer Post', 'Este es el contenido de mi primer post en el blog.', 'etiqueta1, etiqueta2', '2025-11-11 11:27:48', NULL),
(14, 'Primer Post', 'Este es el contenido de mi primer post en el blog.', 'etiqueta1, etiqueta2', '2025-11-11 11:27:48', NULL),
(15, 'Descubrimiento Asombroso', 'Flor río fuego mar puerta.', 'naturaleza, inspiración, aventura', '2025-11-11 13:22:38', NULL),
(70, 'Relato Mágico', 'Gato fuego casa amor río.', 'aventura, misterio', '2025-11-12 08:26:12', NULL),
(71, 'Descubrimiento Asombroso', 'Nube sol perro ventana agua.', 'descubrimiento', '2025-11-12 08:26:14', NULL),
(72, 'Descubrimiento Asombroso', 'Árbol camino puerta ventana luna.', 'aventura, experiencia', '2025-11-12 08:29:29', NULL),
(73, 'Aventura Épica', 'Nube puerta flor camino mar.', 'reflexión', '2025-11-12 08:29:30', NULL),
(74, 'Viaje Extraordinario', 'Jardín flor árbol mar montaña.', 'viaje', '2025-11-12 08:29:31', NULL),
(75, 'Reflexión Profunda', 'Puerta viento montaña mar fuego.', 'viaje', '2025-11-12 08:29:45', NULL),
(76, 'Primer Post', 'Este es el contenido de mi primer post en el blog.', 'etiqueta1, etiqueta2', '2025-11-12 09:27:01', NULL),
(77, 'Primer Post', 'Este es el contenido de mi primer post en el blog.', 'etiqueta1, etiqueta2', '2025-11-12 09:27:11', NULL),
(78, 'Viaje Extraordinario', 'Luna tierra camino río libro.', 'inspiración, descubrimiento, naturaleza', '2025-11-12 09:27:19', NULL),
(79, 'Historia Increíble', 'Árbol agua camino montaña fuego.', 'inspiración, viaje, magia', '2025-11-12 09:27:22', NULL),
(80, 'Inspiración Diaria', 'Nube fuego río luna amor.', 'magia, momento', '2025-11-12 09:27:22', NULL),
(81, 'Más vale tarde que nunca.', 'El cambio es la única constante en la vida. \nEl que no arriesga no gana. La unión hace la fuerza. El que no arriesga no gana. ', 'etiqueta1, etiqueta2', '2025-11-12 09:47:41', NULL),
(82, 'El respeto es la base de toda relación.', 'El conocimiento es poder. A mal tiempo, buena cara. La esperanza es lo último que se pierde. La esperanza es lo último que se pierde. ', 'etiqueta1, etiqueta2', '2025-11-12 09:47:46', NULL),
(83, 'El tiempo es oro.', 'El conocimiento es poder. A mal tiempo, buena cara. El que no arriesga no gana. El conocimiento es poder. La unión hace la fuerza. La unión hace la fuerza. ', 'etiqueta1, etiqueta2', '2025-11-12 09:47:47', NULL),
(84, 'La creatividad es la inteligencia divirtiéndose.', 'A mal tiempo, buena cara. La perseverancia es la clave del éxito. A mal tiempo, buena cara. El respeto es la base de toda relación. \nLa suerte favorece a los audaces. ', 'etiqueta1, etiqueta2', '2025-11-12 09:47:48', NULL),
(85, 'La vida es bella.', 'El que no arriesga no gana. La vida es bella. ', 'etiqueta1, etiqueta2', '2025-11-12 09:47:49', NULL),
(86, 'Más vale tarde que nunca.', 'La perseverancia es la clave del éxito. La creatividad es la inteligencia divirtiéndose. El que no arriesga no gana. La esperanza es lo último que se pierde. La comunicación es clave en cualquier relación. El conocimiento es poder. ', 'etiqueta1, etiqueta2', '2025-11-12 09:47:49', NULL),
(87, 'El conocimiento es poder.', 'La vida es bella. Más vale pájaro en mano que ciento volando. La comunicación es clave en cualquier relación. ', 'etiqueta1, etiqueta2', '2025-11-12 09:47:50', NULL),
(88, 'El tiempo es oro.', 'La perseverancia es la clave del éxito. La comunicación es clave en cualquier relación. A mal tiempo, buena cara. El respeto es la base de toda relación. El tiempo es oro. El conocimiento es poder. \n', 'etiqueta1, etiqueta2', '2025-11-12 09:47:50', NULL),
(89, 'Relato Mágico', 'Agua ventana gato flor montaña.', 'reflexión', '2025-11-12 09:48:01', NULL),
(90, 'Inspiración Diaria', 'Ventana viento mar montaña fuego.', 'misterio', '2025-11-12 09:48:04', NULL),
(91, 'Misterio Fascinante', 'Cielo nube árbol agua luna.', 'inspiración, magia', '2025-11-12 09:48:05', NULL),
(92, 'Inspiración Diaria', 'Gato flor cielo árbol nube.', 'inspiración', '2025-11-12 09:48:05', NULL),
(94, 'Hector mola mucho', 'Mola muchisimo', 'Hola a todos', '2025-11-12 10:18:52', '2025-11-12 10:30:36'),
(95, 'Historia Increíble', 'Río nube el flor estrella.', 'magia, naturaleza', '2025-11-12 10:38:22', NULL),
(96, 'Inspiración Diaria', 'Tierra cielo libro montaña estrella.', 'reflexión', '2025-11-12 10:38:23', NULL),
(97, 'Experiencia Única', 'Gato mar estrella viento flor.', 'aventura, experiencia, reflexión', '2025-11-12 10:38:24', NULL),
(98, 'Experiencia Que Es Tan Única', 'Fuego árbol ventana mar estrella.', 'experiencia, misterio, reflexión', '2025-11-12 10:38:25', '2025-11-20 08:30:11');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ideas`
--

CREATE TABLE `ideas` (
  `id` bigint NOT NULL,
  `titulo` varchar(255) COLLATE utf32_unicode_ci NOT NULL,
  `comentario` text COLLATE utf32_unicode_ci NOT NULL,
  `categoria` enum('IDEA','MEJORA','BUG') COLLATE utf32_unicode_ci NOT NULL,
  `publico` tinyint(1) NOT NULL DEFAULT '1',
  `fecha_creacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_modificacion` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `blog`
--
ALTER TABLE `blog`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ideas`
--
ALTER TABLE `ideas`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `blog`
--
ALTER TABLE `blog`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT de la tabla `ideas`
--
ALTER TABLE `ideas`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;
COMMIT;
