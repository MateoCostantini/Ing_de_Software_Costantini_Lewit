

--module Stack ( Stack, newS, freeCellsS, stackS, netS, holdsS, popS )
--  where

import Palet
import Route

data Stack = Sta [ Palet ] Int deriving (Eq, Show)

newS :: Int -> Stack                      -- construye una Pila con la capacidad indicada 
newS capacidad = Sta [] capacidad

freeCellsS :: Stack -> Int                -- responde la celdas disponibles en la pila
freeCellsS (Sta pila capacidad) = capacidad - length pila

stackS :: Stack -> Palet -> Stack         -- apila el palet indicado en la pila
stackS (Sta pila capacidad) palet | freeCellsS (Sta pila capacidad)  > 0 = (Sta (pila++[palet]) capacidad)
                                    | otherwise = (Sta pila capacidad)
-- Esta creando un nuevo stack sin modificar el anterior. 
-- Habria agregar el palet al mismo stack o se puede borrar el viejo y quedarse con el nuevo?


netS :: Stack -> Int                      -- responde el peso neto de los paletes en la pila
netS (Sta [] _) = 0
--netS (Sta (pila) _) = foldr (\x (Pal _ peso) -> (x+peso)) 0 pila
--netS (Sta (pila) _) = foldr (+) 0 pila
netS (Sta (p:ps) _) = 
--netS (Sta pila  _) = foldr

--holdsS :: Stack -> Palet -> Route -> Bool -- indica si la pila puede aceptar el palet considerando las ciudades en la ruta
--popS :: Stack -> String -> Stack          -- quita del tope los paletes con destino en la ciudad indicada
