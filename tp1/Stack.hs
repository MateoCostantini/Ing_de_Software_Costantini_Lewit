

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
-- stackS deberia usar holdsS para ver que el palet no se baje despues que alguno?


netS :: Stack -> Int                      -- responde el peso neto de los paletes en la pila
netS (Sta [] _) = 0
netS (Sta (p:ps) capacidad) = netP p + netS (Sta ps capacidad)

holdsS :: Stack -> Palet -> Route -> Bool -- indica si la pila puede aceptar el palet considerando las ciudades en la ruta
holdsS (Sta [] _) _ _ = True
holdsS (Sta (p:ps) capacidad) palet ruta    | (inRouteR ruta (destinationP palet) == True) && (inOrderR ruta (destinationP palet) (destinationP p)) = holdsS (Sta ps capacidad) palet ruta
                                            | otherwise = False

popS :: Stack -> String -> Stack          -- quita del tope los paletes con destino en la ciudad indicada
popS (Sta (p:ps) capacidad) destino | destinationP p /= destino = (Sta last (p:ps) capacidad)
                                    | otherwise = pop



p = newP "bsas" 4
p2 = newP "tigre" 8
p3 = newP "belgrano" 2

s = newS 3

r = newR ["bsas", "lujan", "tigre", "CABA", "tucuman"]

