from Car import Car

if __name__ == '__main__':
    print("*** Car Class Test Begin ***")

    test_passed = True
    errors_collection = []

    try:
        my_car = Car('Opel',
                     'Astra',
                     'Black', 2020,
                     {"radio", "fog lights"},
                     111000,
                     "AA123ZZ",
                     "123456",
                     10000,
                     "€")
        print(my_car)
    except Exception as e:
        print("An Error occured! Updating the test ...")
        errors_collection.append(e)
        test_passed = False

    print("Test results:")
    if test_passed:
        print("The test has been passed!")
    else:
        print("The test failed! The following errors occured:\n", errors_collection)


    print("*** Car Class Test Ended ***")