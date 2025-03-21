import Palet 
import Stack
import Route
import Truck

import Control.Exception
import System.IO.Unsafe
import qualified Data.Type.Bool as False

testF :: Show a => a -> Bool
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()


palet1 = newP "bsas" 4
palet2 = newP "tigre" 8
palet3 = newP "belgrano" 2
palet4 = newP "lujan" 1
palet5 = newP "tucuman" 3
palet6 = newP "ciudadInexistente" 5
palet7 = newP "CABA" 4 
palet8 = newP "CABA" 7
palet9 = newP "lujan" 3

p1Ciudad= destinationP palet1
p1Peso = netP palet1

ruta1 = newR ["bsas", "lujan", "tigre", "CABA", "tucuman"]
rutaVacia = newR []
rutaRepetida = newR ["bsas", "lujan", "tigre", "lujan", "CABA"]

stack1 = newS 2
stack2 = stackS stack1 palet7 -- caba
stack3 = stackS stack2 palet2 -- tigre caba
stack4 = stackS stack3 palet4 -- tigra caba (capacidad llena)
stackVacio = newS 0
-- palet1 -> palet2 --> palet4 (ruta1)

stack5 = popS stack3 "tigre" -- caba
stack6 = stackS stack5 palet5 -- tucuman caba (estamos probando que stackS no se fija en que la ruta sea correcta)
stack7 = popS stack6 "CABA"

stackCiuRepetido = newS 5
stackCiuRepetido2 = stackS stackCiuRepetido palet7
stackCiuRepetido3 = stackS stackCiuRepetido2 palet8
stackCiuRepetido4 = popS stackCiuRepetido3 "CABA"

-- ahora probamos el caso en que en el stack haya palets de ciudad A, B, A respectivamente:
stackCiuIntermedia = newS 8
stackCiuIntermedia2 = stackS stackCiuIntermedia palet4
stackCiuIntermedia3 = stackS stackCiuIntermedia2 palet2
stackCiuIntermedia4 = stackS stackCiuIntermedia3 palet9
stackCiuIntermedia5 = popS stackCiuIntermedia4 "lujan"

-- emprolijar/usar lo anterior
p = newP "bsas" 4
p2 = newP "tigre" 8
p3 = newP "belgrano" 2
p4 = newP "lujan" 1
p5 = newP "bsas" 2
s = newS 3

r = newR ["bsas", "lujan", "tigre", "CABA", "tucuman"]
t = newT 1 3 r

s2 = stackS s p2
s3 = stackS s2 p
s4 = newS 3

t2 = loadT t p
t3 = loadT t2 p2
t4 = loadT t3 p4

t5 = unloadT t4 "bsas"
-- hasta aca

tests = [
    -- Palet.hs
    netP palet1 == 4,
    destinationP palet1 == "bsas",
    -- Route.hs: inOrderR
    inOrderR ruta1 "tigre" "tucuman" == True, 
    inOrderR ruta1 "tigre" "lujan" == False,
    inOrderR ruta1 "ciudadInexistente" "tigre" == False,
    inOrderR ruta1 "tigre" "ciudadInexistente" == True,
    inOrderR ruta1 "CABA" "CABA" == True,
    inOrderR rutaVacia "tigre" "bsas" == False,
    -- Route.hs: inRouteR
    inRouteR ruta1 "tigre" == True,
    inRouteR ruta1 "ciudadInexistente" == False,
    inRouteR rutaVacia "tucuman" == False,
    -- Stack.hs
    freeCellsS stack1 == 2,
    freeCellsS stack2 == 1,
    freeCellsS stack3 == 0,
    freeCellsS stack4 == 0,
    freeCellsS stackVacio == 0,
    stack3 == stack4, -- preguntamos emilio
    freeCellsS stack3 == freeCellsS stack4,
    netS stack3 == netS stack4,
    netS stack2 == 4,
    netS stack3 == 4+8,
    holdsS stack3 palet1 ruta1 == True,
    holdsS stack3 palet7 ruta1 == False,
    freeCellsS stack6 == 0, -- verificamos que stack6 este lleno por mas que la ruta no lo permita (stackS no tiene en cuenta ruta, holdsS si)
    netS stack6 == 4+3, -- verificamos lo mismo con el peso
    holdsS stack6 palet2 rutaVacia == False, -- holdsS usa inRouteR, como la ciudad de palet2 no esta en rutaVacia da False.
    freeCellsS stack6 == freeCellsS stack7, -- al popear una ciudad que no es la actual, no hace nada
    freeCellsS stackCiuRepetido3 == 5-2,
    freeCellsS stackCiuRepetido4 == 5, -- probamos que se poppeen los dos pallets de la misma ciudad
    -- si queremos agregar un palet mas a un stack lleno ==> queremos que devuelva el mismo stack
    freeCellsS stackCiuIntermedia4 == 5, -- ahora probamos el caso en que en el stack haya palets de ciudad A, B, A respectivamente
    freeCellsS stackCiuIntermedia5 == 6, -- probamos que solamente saque del stack el palet de lujan que esta arriba y no los palets de lujan que estan abajo que otras ciudades.
    testF(loadT t2 p2) -- CAMBIAR Y HACER PROLIJO
    

    ]

